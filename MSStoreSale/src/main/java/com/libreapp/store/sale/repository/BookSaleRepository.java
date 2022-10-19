package com.libreapp.store.sale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import com.libreapp.store.sale.bean.BookSale;

public interface BookSaleRepository extends CrudRepository<BookSale, Long> {

	public List<BookSale> findByState(String state);
	public BookSale findByNumberInvoice(String numberInvoice);
	
	@Procedure(name = "sp_numberInvoice")
	public String generateNumerInvoice();
	
}
