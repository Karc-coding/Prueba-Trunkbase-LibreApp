package com.libreapp.store.customer.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@ApiModelProperty(name = "id", value = "ID de usuario", example = "097ec881-59d6-11ec-802c-0a0027000006", notes = "ID de usuario", dataType = "string(UUID)",
					position = 0, required = true)
	private String id;

	@ApiModelProperty(name = "dni", value = "Numero de DNI", example = "78456985", notes = "Numero de DNI", dataType = "string",
					position = 1, required = true)
	@NotEmpty(message = "El campo dni no puede estar vacio")
	@Size(min = 8, max = 8, message = "El campo dni debe tener 8 caracteres")
	@Column(unique = true, nullable = false, length = 8)
	private String dni;

	@ApiModelProperty(name = "firstName", value = "Nombre", example = "Kevin Alexander", notes = "Nombre", dataType = "string",
					position = 2, required = true)
	@NotEmpty(message = "El campo nombre no puede estar vacio")
	@Size(max = 50, message = "El campo nombre acepta un maximo de 50 caracteres")
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@ApiModelProperty(name = "lastName", value = "Apellido", example = "Rojas Cobeñas", notes = "Apellido", dataType = "string",
					position = 3, required = true)
	@NotEmpty(message = "El campo apellido no puede estar vacio")
	@Size(max = 50, message = "El campo apellido acepta un maximo de 50 caracteres")
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@ApiModelProperty(name = "email", value = "Email", example = "kevinrojascobenas@gmail.com", notes = "Email", dataType = "string",
					position = 4, required = true)
	@NotEmpty(message = "El campo correo no puede estar vacio")
	@Email(message = "El formato del correo esta mal realizada")
	@Size(max = 100, message = "El campo correo acepta un maximo de 100 caracteres")
	@Column(nullable = false, length = 100)
	private String email;

	@ApiModelProperty(name = "phone", value = "Telefono", example = "957485658", notes = "Telefono", dataType = "string",
					position = 5, required = false)
	@Size(max = 20, message = "El campo telefono acepta un maximo de 20 caracteres")
	@Column(nullable = true, length = 20)
	private String phone;

	@ApiModelProperty(name = "address", value = "Direccion", example = "Avenida Jorge Basadre 498, San Isidro, Lima 27, Perú", notes = "Direccion", dataType = "string",
					position = 6, required = false)
	@Size(max = 100, message = "El campo direccion acepta un maximo de 100 caracteres")
	@Column(nullable = true, length = 100)
	private String address;

	@ApiModelProperty(name = "dateBirth", value = "Fecha de nacimiento", example = "2004-09-15", notes = "Fecha de nacimiento", dataType = "Date",
					position = 7, required = false)
	@Temporal(TemporalType.DATE)
	@Past(message = "El campo fecha de cumpleaños no acepta fechas futuras")
	@Column(name = "date_birth", nullable = true)
	private Date dateBirth;

	@ApiModelProperty(name = "departamento", value = "Departamento", notes = "Departamento", dataType = "Departamento",
					position = 8, required = true)
	@NotNull(message = "El departamento no puede estar vacio")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departments_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Departamento departamento;

	@ApiModelProperty(name = "dateRegister", value = "Fecha de registro", example = "2021-12-08", notes = "Fecha de registro", dataType = "Date",
					position = 9, required = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_register", nullable = true)
	private Date dateRegister;


}
