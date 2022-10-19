package com.libreapp.store.customer.service;

import java.util.List;

import com.libreapp.store.customer.bean.Usuario;

public interface UsuarioService {
	
	public abstract List<Usuario> listAll();
	public abstract Usuario createUsuario(Usuario usuario);
	public abstract Usuario updateUsuario(Usuario usuario);
	public abstract Usuario deleteUsuario(String id);
	public abstract Usuario getUsuario(String id);

}
