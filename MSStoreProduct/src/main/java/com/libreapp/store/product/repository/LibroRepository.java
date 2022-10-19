package com.libreapp.store.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libreapp.store.product.bean.Libro;

public interface LibroRepository extends JpaRepository<Libro, String> {

	public abstract List<Libro> findByState(String state);
	
}
