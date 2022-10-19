package com.libreapp.store.product.service;

import java.util.List;

import com.libreapp.store.product.bean.Categoria;

public interface CategoriaService {

	public abstract List<Categoria> listAll();
	public abstract Categoria getCategory(Long id);
	
}
