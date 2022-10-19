package com.libreapp.store.sale.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.libreapp.store.sale.bean.BookSale;
import com.libreapp.store.sale.bean.BookSaleDetails;
import com.libreapp.store.sale.client.CustomerClient;
import com.libreapp.store.sale.client.ProductClient;
import com.libreapp.store.sale.client.model.Libro;
import com.libreapp.store.sale.client.model.Usuario;
import com.libreapp.store.sale.repository.BookSaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookSaleServiceImpl implements BookSaleService {

	@Autowired
	private BookSaleRepository repo;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private CustomerClient customerClient;

	@Autowired
	private ProductClient productClient;
	
	@Override
	public List<BookSale> listAll() {
		return (List<BookSale>) repo.findAll();
	}

	@Override
	public BookSale createBookSale(BookSale bookSale) {
		return repo.save(bookSale);
	}

	@Override
	public BookSale updateBookSale(BookSale bookSale) {
		BookSale bs = getBookSale(bookSale.getId());
		if (bs == null) {
			return null;
		}
		return repo.save(bookSale);
	}

	@Override
	public BookSale deleteBookSale(Long id) {
		BookSale bs = getBookSale(id);
		if (bs == null) {
			return null;
		}
		bs.setState("ELIMINADO");
		return repo.save(bs);
	}

	@Override
	public BookSale getBookSale(Long id) {
		return getClientFeign(id);
	}

	@Override
	public List<BookSale> listAllForState(String state) {
		return repo.findByState(state);
	}

	@Override
	public BookSale getBookSaleForNumberInvoice(String numberInvoice) {
		BookSale bs = repo.findByNumberInvoice(numberInvoice);
		if (bs == null) {
			return null;
		}
		return getClientFeign(bs.getId());
	}

	@Override
	public String generateNumberInvoice() {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_NUMBER_INVOICE");
		
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
		query.execute();
		String numberInvoice = (String) query.getOutputParameterValue(1);
		return numberInvoice;
	}

	@Override
	public BookSale getClientFeign(Long id) {
		Optional<BookSale> bookSaleOptional = repo.findById(id);
		if (bookSaleOptional.isPresent()) {
			BookSale bookSale = bookSaleOptional.get();
			
			ResponseEntity<Usuario> usuarioEntity = customerClient.getCustomerFeign(bookSale.getUser());
			Usuario user = Optional.ofNullable(usuarioEntity.getBody()).orElse(new Usuario());
			bookSale.setUsuario(user);
			
			List<BookSaleDetails> listItems = bookSale.getBookSaleDetails().stream().map(items -> {
				ResponseEntity<Libro> libroEntity = productClient.getProductFeign(items.getBookId());
				Libro book = Optional.ofNullable(libroEntity.getBody()).orElse(new Libro());
				items.setLibro(book);
				return items;
			}).collect(Collectors.toList());
			
			bookSale.setBookSaleDetails(listItems);
			return bookSale;
		}
		return null;
	}

}
