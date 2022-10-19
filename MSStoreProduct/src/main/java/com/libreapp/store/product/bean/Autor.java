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
@Table(name = "authors")
public class Autor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(name = "id", value = "ID de autor", example = "41", notes = "ID de autor", dataType = "long",
					position = 0, required = true)
	private Long id;

	@ApiModelProperty(name = "firstName", value = "Nombre", example = "Kevin Alexander", notes = "Nombre", dataType = "string",
					position = 1, required = false)
	@Size(max = 50, message = "El campo nombre acepta un maximo de 50 caracteres")
	@Column(name = "first_name", nullable = true, length = 50)
	private String firstName;

	@ApiModelProperty(name = "lastName", value = "Apellido", example = "Rojas Cobeñas", notes = "Apellido", dataType = "string",
					position = 2, required = false)
	@Size(max = 50, message = "El campo apellido acepta un maximo de 50 caracteres")
	@Column(name = "last_name", nullable = true, length = 50)
	private String lastName;
	
	@ApiModelProperty(name = "nickname", value = "Nickname", example = "Karc", notes = "Nickname", dataType = "string",
					position = 3, required = true)
	@Size(max = 60, message = "El campo apodo acepta un maximo de 60 caracteres")
	@Column(nullable = false, length = 60)
	private String nickname;
	
}
