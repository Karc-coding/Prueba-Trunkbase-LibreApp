package com.libreapp.store.product.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Categoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(name = "id", value = "ID de categoria", example = "31", notes = "ID de categoria", dataType = "long",
					position = 0, required = true)
	private Long id;
	

	@ApiModelProperty(name = "name", value = "Nombre", example = "Horror", notes = "Nombre", dataType = "string",
					position = 1, required = true)
	@Size(max = 75, message = "El campo categoria acepta un maximo de 75 caracteres")
	@Column(name = "name_category", unique = true, nullable = false, length = 75)
	private String name;
	
}
