package com.libreapp.store.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libreapp.store.product.bean.Libro;
import com.libreapp.store.product.repository.LibroRepository;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroRepository repo;
	
	@Override
	public List<Libro> listAll() {
		return repo.findAll();
	}

	@Override
	public Libro createLibro(Libro libro) {
		libro.setState("CREATED");
		return repo.save(libro);
	}

	@Override
	public Libro updateLibro(Libro libro) {
		Libro book = getLibro(libro.getId());
		if (book == null) {
			return null;
		}
		return repo.save(libro);
	}

	@Override
	public Libro deleteLibro(String id) {
		Libro book = getLibro(id);
		if (book == null) {
			return null;
		}
		book.setState("ELIMINATE");
		return repo.save(book);
	}

	@Override
	public Libro getLibro(String id) {
		return repo.findById(id).orElse(null);
	}

//	---
	
	@Override
	public List<Libro> listAllActive() {
		return repo.findByState("CREATED");
	}

}
