package com.libreapp.store.sale.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libreapp.store.sale.client.model.Usuario;

//fallback = CustomerClientFallback.class
@FeignClient(name = "CONFIG-CUSTOMER")
@RequestMapping("/customer")
public interface CustomerClient {
	
	@GetMapping("/feign/{id}")
	public ResponseEntity<Usuario> getCustomerFeign(@PathVariable("id") String id);
	
}
