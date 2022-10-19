package com.libreapp.store.sale.client.model;

import lombok.Data;

@Data
public class Libro {

//	private String id;
	private String title;
	private String serie;
	private String yearBook;
	private Categoria categoria;
	private double price;
	private int stock;
	private Autor autor;
	private String state;
	
}