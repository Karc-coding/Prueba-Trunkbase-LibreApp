package com.libreapp.store.product.service;

import java.util.List;

import com.libreapp.store.product.bean.Autor;

public interface AutorService {

	public abstract List<Autor> listAll();
	public abstract Autor getAutor(Long id);
	
}
