package com.libreapp.store.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.libreapp.store.product.bean.Categoria;
import com.libreapp.store.product.service.CategoriaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;


	@ApiOperation(value = "Get Category by id", notes = "Respuesta del sistema y el cuerpo del category")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getCategory(@PathVariable("id") Long id) {

		Map<String, Object> result = new HashMap<>();

		try {
			Categoria categoria = categoriaService.getCategory(id);
			if (categoria == null) {
				result.put("message", "No existe la categoria con el id " + id);
			} else {
				result.put("data", categoria);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	
	@ApiOperation(value = "List All Categories", notes = "Respuesta del sistema y el listado de categories")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@GetMapping("/listAll")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listAllCategory() {

		Map<String, Object> result = new HashMap<>();

		try {
			List<Categoria> list = categoriaService.listAll();
			if (CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				result.put("list", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

}
