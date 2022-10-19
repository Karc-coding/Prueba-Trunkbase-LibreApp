package com.libreapp.store.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libreapp.store.product.bean.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	
	
}
