package com.libreapp.store.sale.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.libreapp.store.sale.client.model.Libro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "book_sale_details")
public class BookSaleDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(name = "id", value = "ID de book sale details", example = "72", notes = "ID de book sale details", dataType = "long",
					position = 0, required = true)
	private Long id;

	@ApiModelProperty(name = "bookSalesId", value = "ID de book sale", example = "71", notes = "ID de book sale", dataType = "long",
					position = 1, required = true)
	@PositiveOrZero(message = "El campo id de venta debe tener numeros positivos")
	@Column(name = "book_sales_id", nullable = false)
	private Long bookSalesId;
	
	@ApiModelProperty(name = "bookId", value = "ID de libro", example = "097ec881-59d6-11ec-802c-0a0027000006", notes = "ID de libro", dataType = "string(UUID)",
					position = 2, required = true)
	@Size(min = 36, max = 36, message = "El campo usuario debe tener 36 caracteres")
	@Column(name = "books_id", nullable = false, length = 36)
	private String bookId;

	@ApiModelProperty(hidden = true)
	@Transient
	private Libro libro;
	
	@ApiModelProperty(name = "price", value = "Precio", example = "54.60", notes = "Precio", dataType = "double",
					position = 3, required = true)
	@Digits(integer = 7, fraction = 2, message = "El campo precio acepta un maximo de 7 numeros enteros y 2 decimales")
	@PositiveOrZero(message = "El campo precio debe tener numeros positivos")
	@Column(nullable = false, scale = 2, precision = 9)
	private double price;
	
	@ApiModelProperty(name = "amount", value = "Cantidad", example = "4", notes = "Cantidad", dataType = "int",
					position = 4, required = true)
	@Digits(integer = 9, fraction = 0, message = "El campo cantidad acepta un maximo de 9 numeros enteros")
	@PositiveOrZero(message = "El campo cantidad debe tener numeros positivos")
	@Column(nullable = false, precision = 9)
	private int amount;
	
	@ApiModelProperty(name = "subtotal", value = "Subtotal", example = "218.40", notes = "Subtotal", dataType = "double",
					position = 5, required = true)
	@Digits(integer = 7, fraction = 2, message = "El campo subtotal acepta un maximo de 7 numeros enteros y 2 decimales")
	@PositiveOrZero(message = "El campo subtotal debe tener numeros positivos")
	@Column(nullable = false, scale = 2, precision = 9)
	private double subtotal;

	
	public double getSubtotal() {
		return this.subTotal();
	}
	
	public double subTotal() {
		if (this.price > 0 && this.amount > 0) {
			return this.price * this.amount;
		} else {
			return 0;
		}
	}
	
}
