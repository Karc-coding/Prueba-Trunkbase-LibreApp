package com.libreapp.store.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libreapp.store.product.bean.Autor;
import com.libreapp.store.product.repository.AutorRepository;

@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	private AutorRepository repo;
	
	@Override
	public List<Autor> listAll() {
		return repo.findAll();
	}

	@Override
	public Autor getAutor(Long id) {
		return repo.findById(id).orElse(null);
	}

}
