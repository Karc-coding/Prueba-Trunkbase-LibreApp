package com.libreapp.store.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libreapp.store.product.bean.Categoria;
import com.libreapp.store.product.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	@Override
	public List<Categoria> listAll() {
		return repo.findAll();
	}

	@Override
	public Categoria getCategory(Long id) {
		return repo.findById(id).orElse(null);
	}

}
