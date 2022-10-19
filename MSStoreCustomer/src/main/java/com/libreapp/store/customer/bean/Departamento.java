package com.libreapp.store.customer.bean;

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
@Table(name = "departments")
public class Departamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(name = "id", value = "ID de departamento", example = "55", notes = "ID de departamento", dataType = "long",
					position = 0, required = true)
	private Long id;
	
	@ApiModelProperty(name = "name", value = "Nombre", example = "La Libertad", notes = "Nombre", dataType = "string",
					position = 1, required = true)
	@Size(max = 50, message = "El campo nombre acepta un maximo de 50 caracteres")
	@Column(name = "name_department", unique = true, nullable = false, length = 50)
	private String name;
	
}
