package com.libreapp.store.sale.client.model;

import java.util.Date;

import lombok.Data;

@Data
public class Usuario {

//	private String id;
	private String dni;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private Date dateBirth;
	private Date dateRegister;
	private Departamento departamento;
	
}
