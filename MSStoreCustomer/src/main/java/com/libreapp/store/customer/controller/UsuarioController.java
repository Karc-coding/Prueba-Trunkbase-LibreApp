package com.libreapp.store.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.libreapp.store.customer.bean.Usuario;
import com.libreapp.store.customer.service.UsuarioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	
	@ApiOperation(value = "Get Customer by id", notes = "Respuesta del sistema y el cuerpo del customer")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getCustomer(@PathVariable("id") String id) {

		Map<String, Object> result = new HashMap<>();

		try {
			Usuario usuario = usuarioService.getUsuario(id);
			if (usuario == null) {
				result.put("message", "No existe el cliente con el id indicado");
			} else {
				result.put("data", usuario);
				result.put("message", "Se ha encontrado un cliente con el id indicado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	
	@ApiOperation(value = "List All Customers", notes = "Respuesta del sistema y el listado de customers")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@GetMapping("/listAll")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listAllCustomers() {

		Map<String, Object> result = new HashMap<>();

		try {
			List<Usuario> list = usuarioService.listAll();
			if (CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				result.put("list", list);
				result.put("message", "Se encontro " + list.size() + " cliente(s)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}
	

	@ApiOperation(value = "Create Customer", notes = "Respuesta del sistema y el cuerpo del customer")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> createCustomer(@RequestBody Usuario user) {

		Map<String, Object> result = new HashMap<>();

		try {
			Usuario usuario = usuarioService.createUsuario(user);
			if (usuario == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			} else {
				result.put("data", usuario);
				result.put("message", "Se ha creado un cliente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	
	@ApiOperation(value = "Update Customer", notes = "Respuesta del sistema y el cuerpo del customer")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateCustomer(@PathVariable("id") String id,
			@RequestBody Usuario user) {

		Map<String, Object> result = new HashMap<>();

		try {
			user.setId(id);
			Usuario usuario = usuarioService.updateUsuario(user);
			if (usuario == null) {
				result.put("message", "No existe el cliente con el id indicado");
			} else {
				result.put("data", usuario);
				result.put("message", "Se ha actualizado un cliente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}
	

	@ApiOperation(value = "Delete Customer by id", notes = "Respuesta del sistema y el cuerpo del customer")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteCustomer(@PathVariable("id") String id) {

		Map<String, Object> result = new HashMap<>();

		try {
			Usuario usuario = usuarioService.deleteUsuario(id);
			if (usuario == null) {
				result.put("message", "No existe el cliente con el id indicado");
			} else {
				result.put("data", usuario);
				result.put("message", "Se ha eliminado un cliente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}
	
	//-----
	
	@ApiOperation(value = "Get Customer by id", notes = "Cuerpo del customer")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@GetMapping("/feign/{id}")
	public ResponseEntity<Usuario> getCustomerFeign(@PathVariable("id") String id) {

		Usuario usuario = usuarioService.getUsuario(id);

		try {
			if (usuario == null) {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(usuario);
	}

}
