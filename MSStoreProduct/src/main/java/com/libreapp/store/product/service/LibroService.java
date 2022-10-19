package com.libreapp.store.product.service;

import java.util.List;

import com.libreapp.store.product.bean.Libro;

public interface LibroService {

	public abstract List<Libro> listAll();
	public abstract Libro createLibro(Libro libro);
	public abstract Libro updateLibro(Libro libro);
	public abstract Libro deleteLibro(String id);
	public abstract Libro getLibro(String id);
	
	public abstract List<Libro> listAllActive();
	
}
