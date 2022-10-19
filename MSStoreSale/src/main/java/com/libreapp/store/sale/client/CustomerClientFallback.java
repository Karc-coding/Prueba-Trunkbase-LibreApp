package com.libreapp.store.sale.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.libreapp.store.sale.client.model.Usuario;

//@Component
public class CustomerClientFallback implements CustomerClient {

	@Override
	public ResponseEntity<Usuario> getCustomerFeign(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
