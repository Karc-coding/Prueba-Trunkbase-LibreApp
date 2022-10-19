package com.libreapp.store.sale.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.libreapp.store.sale.client.model.Libro;

//@Component
public class ProductClientFallback implements ProductClient {

	@Override
	public ResponseEntity<Libro> getProductFeign(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
