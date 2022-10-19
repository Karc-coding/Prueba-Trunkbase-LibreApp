package com.libreapp.store.sale.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libreapp.store.sale.client.model.Libro;

//fallback = ProductClientFallback.class
@FeignClient(name = "CONFIG-PRODUCT")
@RequestMapping("/book")
public interface ProductClient {
	
	@GetMapping("/feign/{id}")
	public ResponseEntity<Libro> getProductFeign(@PathVariable("id") String id);
	
}
