package com.libreapp.store.product.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Libro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@ApiModelProperty(name = "id", value = "ID de libro", example = "097ec881-59d6-11ec-802c-0a0027000006", notes = "ID de libro", dataType = "string(UUID)",
					position = 0, required = true)
	private String id;

	@ApiModelProperty(name = "title", value = "Titulo", example = "The World!", notes = "Titulo", dataType = "string",
					position = 1, required = true)
	@NotEmpty(message = "El campo titulo no puede estar vacio")
	@Size(max = 125, message = "El campo titulo acepta un maximo de 125 caracteres")
	@Column(nullable = false, length = 125)
	private String title;

	@ApiModelProperty(name = "serie", value = "Serie", example = "VJ28483", notes = "Serie", dataType = "string",
					position = 2, required = true)
	@NotEmpty(message = "El campo serie no puede estar vacio")
	@Size(min = 7, max = 7, message = "El campo serie debe tener 7 caracteres")
	@Column(unique = true, nullable = false, length = 7)
	private String serie;

	@ApiModelProperty(name = "yearBook", value = "Año del libro", example = "2005", notes = "Año del libro", dataType = "string",
					position = 3, required = false)
	@Size(min = 4, max = 4, message = "El campo año debe tener 4 caracteres")
	@Column(name = "year_book", nullable = true, length = 4)
	private String yearBook;

	@ApiModelProperty(name = "categoria", value = "Categoria", notes = "Categoria", dataType = "Categoria",
					position = 4, required = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categories_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Categoria categoria;

	
	@ApiModelProperty(name = "price", value = "Precio", example = "54.60", notes = "Precio", dataType = "double",
					position = 5, required = true)
	@Digits(integer = 7, fraction = 2, message = "El campo precio acepta un maximo de 7 numeros enteros y 2 decimales")
	@PositiveOrZero(message = "El campo precio debe tener numeros positivos")
	@Column(nullable = false, scale = 2, precision = 9)
	private double price;

	@ApiModelProperty(name = "stock", value = "Stock", example = "125", notes = "Stock", dataType = "int",
					position = 6, required = true)
	@Digits(integer = 9, fraction = 0, message = "El campo stock acepta un maximo de 9 numeros enteros")
	@PositiveOrZero(message = "El campo stock debe tener numeros positivos")
	@Column(nullable = false, precision = 9)
	private int stock;

	@ApiModelProperty(name = "autor", value = "Autor", notes = "Autor", dataType = "Autor",
					position = 7, required = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authors_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Autor autor;

	@ApiModelProperty(name = "state", value = "Estado", example = "CREATED", notes = "Estado", dataType = "string",
					position = 8, required = true)
	@Size(max = 10, message = "El campo estado acepta un maximo de 10 caracteres")
	@Column(nullable = false, length = 10)
	private String state;
	
}