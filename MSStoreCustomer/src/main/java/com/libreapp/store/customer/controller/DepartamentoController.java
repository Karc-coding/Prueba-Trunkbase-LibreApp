package com.libreapp.store.customer.controller;

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

import com.libreapp.store.customer.bean.Departamento;
import com.libreapp.store.customer.service.DepartamentoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;

	
	@ApiOperation(value = "Get Department by id", notes = "Respuesta del sistema y el cuerpo del department")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getDepartment(@PathVariable("id") Long id) {

		Map<String, Object> result = new HashMap<>();

		try {
			Departamento departamento = departamentoService.getDepartamento(id);
			if (departamento == null) {
				result.put("message", "No existe el departamento con el id " + id);
			} else {
				result.put("data", departamento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}


	@ApiOperation(value = "List All Departments", notes = "Respuesta del sistema y el listado de departments")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@GetMapping("/listAll")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listAllDepartments() {

		Map<String, Object> result = new HashMap<>();

		try {
			List<Departamento> list = departamentoService.listAll();
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
