package com.libreapp.store.customer.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libreapp.store.customer.bean.Usuario;
import com.libreapp.store.customer.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public List<Usuario> listAll() {
		return repo.findAll();
	}

	@Override
	public Usuario createUsuario(Usuario usuario) {
		usuario.setDateRegister(new Date());
		
		return repo.save(usuario);
	}

	@Override
	public Usuario updateUsuario(Usuario usuario) {
		Usuario user = getUsuario(usuario.getId());
		if (user == null) {
			return null;
		}
		return repo.save(usuario);
	}

	@Override
	public Usuario deleteUsuario(String id) {
		Usuario user = getUsuario(id);
		if (user == null) {
			return null;
		}
		repo.deleteById(id);
		return user;
	}

	@Override
	public Usuario getUsuario(String id) {
		return repo.findById(id).orElse(null);
	}

}
