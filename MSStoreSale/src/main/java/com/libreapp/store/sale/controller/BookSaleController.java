package com.libreapp.store.sale.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

import com.libreapp.store.sale.bean.BookSale;
import com.libreapp.store.sale.bean.BookSaleDetails;
import com.libreapp.store.sale.service.BookSaleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/sale")
@CrossOrigin(origins = "http://localhost:4200")
public class BookSaleController {

	@Autowired
	private BookSaleService bookSaleService;


	@ApiOperation(value = "Get Book Sale by id", notes = "Respuesta del sistema y el cuerpo del book sale")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getBookSale(@PathVariable("id") Long id) {

		Map<String, Object> result = new HashMap<>();

		try {
			BookSale bookSale = bookSaleService.getBookSale(id);
			if (bookSale == null) {
				result.put("message", "No existe el registro de venta con el id indicado");
			} else {
				result.put("data", bookSale);
				result.put("message", "Se ha encontrado un registro de venta con el id indicado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	
	@ApiOperation(value = "List All Book Sale", notes = "Respuesta del sistema y el listado de book sales")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@GetMapping("/listAll")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listAllBookSale() {

		Map<String, Object> result = new HashMap<>();

		try {
			List<BookSale> list = bookSaleService.listAll();
			if (CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				result.put("list", list);
				result.put("message", "Se encontro " + list.size() + " registro(s) de venta(s)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}


	@ApiOperation(value = "Create Book Sale", notes = "Respuesta del sistema y el cuerpo del book sale")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> createBookSale(@RequestBody BookSale bookSale) {

		Map<String, Object> result = new HashMap<>();

		// --- Con el ForEach obtiene cada uno de los BookSaleDetails y registra el subtotal

		List<BookSaleDetails> listBsd = new ArrayList<BookSaleDetails>();
		for (BookSaleDetails sd : bookSale.getBookSaleDetails()) {
			sd.setSubtotal(sd.subTotal());
			listBsd.add(sd);
		}
		bookSale.setBookSaleDetails(listBsd);

		// --- Registra el numero de factura generado por el procedimiento almacenado

		bookSale.setNumberInvoice(bookSaleService.generateNumberInvoice());

		// --- Genera la fecha de registro

		LocalDateTime ldt = LocalDateTime.now();
		bookSale.setDateRegister(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));

		try {
			BookSale bs = bookSaleService.createBookSale(bookSale);
			if (bs == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			} else {
				result.put("data", bs);
				result.put("message", "Se ha creado un registro de venta");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}


	@ApiOperation(value = "Update Book Sale", notes = "Respuesta del sistema y el cuerpo del book sale")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateBookSale(@PathVariable("id") Long id,
			@RequestBody BookSale bookSale) {

		Map<String, Object> result = new HashMap<>();

		// --- Con el ForEach obtiene cada uno de los BookSaleDetails y registra el subtotal

		List<BookSaleDetails> listBsd = new ArrayList<BookSaleDetails>();
		for (BookSaleDetails sd : bookSale.getBookSaleDetails()) {
			sd.setSubtotal(sd.subTotal());
			listBsd.add(sd);
		}
		bookSale.setBookSaleDetails(listBsd);

		try {
			bookSale.setId(id);
			BookSale bs = bookSaleService.updateBookSale(bookSale);
			if (bs == null) {
				result.put("message", "No existe el registro de venta con el id indicado");
			} else {
				result.put("data", bs);
				result.put("message", "Se ha actualizado un registro de venta");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}


	@ApiOperation(value = "Delete Book Sale by id", notes = "Respuesta del sistema y el cuerpo del book sale")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteBookSale(@PathVariable("id") Long id) {

		Map<String, Object> result = new HashMap<>();

		try {
			BookSale bs = bookSaleService.deleteBookSale(id);
			if (bs == null) {
				result.put("message", "No existe el registro de venta con el id indicado");
			} else {
				result.put("data", bs);
				result.put("message", "Se ha eliminado un registro de venta");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

//---

	@ApiOperation(value = "List All Book Sale by state", notes = "Respuesta del sistema y el listado de book sales")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@GetMapping("/listAllForState/{state}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listAllForStateBookSale(@PathVariable("state") String state) {

		Map<String, Object> result = new HashMap<>();

		try {
			List<BookSale> list = bookSaleService.listAllForState(state);
			if (CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			} else {
				result.put("list", list);
				result.put("message", "Se encontro " + list.size() + " registro(s) de venta(s)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}
	

	@ApiOperation(value = "Get Book Sale by number invoice", notes = "Respuesta del sistema y el cuerpo del book sale")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error del sistema")
	})
	@GetMapping("/getNumberInvoice/{numberInvoice}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getBookSaleForNumberInvoice(@PathVariable("numberInvoice") String numberInvoice) {

		Map<String, Object> result = new HashMap<>();

		try {
			BookSale bookSale = bookSaleService.getBookSaleForNumberInvoice(numberInvoice);
			if (bookSale == null) {
				result.put("message", "No existe el registro de venta con el numero de factura indicado");
			} else {
				result.put("data", bookSale);
				result.put("message", "Se ha encontrado un registro de venta con el numero de factura indicado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

}
