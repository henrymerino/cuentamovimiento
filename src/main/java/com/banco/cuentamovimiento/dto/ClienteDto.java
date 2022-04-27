package com.banco.cuentamovimiento.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClienteDto implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String contrasenia; 
	
	private Boolean estado;
	
	private String nombre;

	private String genero;

	private Integer edad;

	private String identificacion;

	private String direccion;

	private String telefono;
	
	private String codError;
	
	private String descripcionError;

}
