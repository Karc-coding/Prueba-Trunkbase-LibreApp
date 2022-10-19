package com.libreapp.store.sale.service;

import java.util.List;

import com.libreapp.store.sale.bean.BookSale;

public interface BookSaleService {

	public abstract List<BookSale> listAll();
	public abstract BookSale createBookSale(BookSale bookSale);
	public abstract BookSale updateBookSale(BookSale bookSale);
	public abstract BookSale deleteBookSale(Long id);
	public abstract BookSale getBookSale(Long id);
	
	public abstract List<BookSale> listAllForState(String state);
	public abstract BookSale getBookSaleForNumberInvoice(String numberInvoice);
	
	public abstract String generateNumberInvoice();
	public abstract BookSale getClientFeign(Long id);
	
}
