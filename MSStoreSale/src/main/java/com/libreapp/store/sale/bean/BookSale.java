package com.libreapp.store.sale.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.libreapp.store.sale.client.model.Usuario;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name = "sp_numberInvoice",
			procedureName = "SP_NUMBER_INVOICE",
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "out_invoice", type = String.class)
			})
})
@Data
@Entity
@Table(name = "book_sales")
public class BookSale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(name = "id", value = "ID de book sale", example = "71", notes = "ID de book sale", dataType = "long",
					position = 0, required = true)
	private Long id;

	@ApiModelProperty(name = "numberInvoice", value = "Numero de Factura", example = "F-202100071", notes = "Numero de Factura", dataType = "string",
					position = 1, required = true)
	@Size(min = 11, max = 11, message = "El campo numero de factura debe tener 11 caracteres")
	@Column(name = "number_invoice", unique = true, nullable = false, length = 11)
	private String numberInvoice;
	
	@ApiModelProperty(name = "description", value = "Descripcion", example = "Descripcion de factura", notes = "Descripcion", dataType = "string",
					position = 2, required = false)
	@Column(name = "description_sales", nullable = true)
	private String description;
	
	
	@ApiModelProperty(name = "user", value = "ID de usuario", example = "097ec881-59d6-11ec-802c-0a0027000006", notes = "ID de usuario", dataType = "string(UUID)",
					position = 3, required = true)
	@Size(min = 36, max = 36, message = "El campo usuario debe tener 36 caracteres")
	@Column(name = "users_id", nullable = false, length = 36)
	private String user;

	@ApiModelProperty(hidden = true)
	@Transient
	private Usuario usuario;

	@Valid
	@NotEmpty(message = "El detalle no puede estar vacio")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "book_sales_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ApiModelProperty(name = "bookSaleDetails", value = "Detalles de book sale", notes = "Detalles de BookSale", dataType = "List<BookSaleDetails>",
					position = 4, required = true)
	private List<BookSaleDetails> bookSaleDetails;
	
	@ApiModelProperty(name = "state", value = "Estado", example = "CREATED", notes = "Estado", dataType = "string",
					position = 5, required = true)
	@Size(max = 10, message = "El campo estado acepta un maximo de 10 caracteres")
	@Column(nullable = false, length = 10)
	private String state;	

	@ApiModelProperty(name = "dateRegister", value = "Fecha de registro", example = "2021-12-10", notes = "Fecha de registro", dataType = "string",
					position = 6, required = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_register", nullable = true)
	private Date dateRegister;
	
}
