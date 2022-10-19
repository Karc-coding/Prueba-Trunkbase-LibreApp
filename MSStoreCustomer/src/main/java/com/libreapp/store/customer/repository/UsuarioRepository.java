package com.libreapp.store.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libreapp.store.customer.bean.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	
	
}
