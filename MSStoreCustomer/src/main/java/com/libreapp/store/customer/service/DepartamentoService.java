package com.libreapp.store.customer.service;

import java.util.List;

import com.libreapp.store.customer.bean.Departamento;

public interface DepartamentoService {

	public abstract List<Departamento> listAll();
	public abstract Departamento getDepartamento(Long id);
	
}
