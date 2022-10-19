package com.libreapp.store.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libreapp.store.customer.bean.Departamento;
import com.libreapp.store.customer.repository.DepartamentoRepository;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoRepository repo;
	
	@Override
	public List<Departamento> listAll() {
		return repo.findAll();
	}

	@Override
	public Departamento getDepartamento(Long id) {
		return repo.findById(id).orElse(null);
	}

}
