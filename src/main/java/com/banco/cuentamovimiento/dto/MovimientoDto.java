package com.banco.cuentamovimiento.dto;

import lombok.Data;

@Data
public class MovimientoDto {

	private Float valor;

	private Long numeroCuenta;

	private String identificacion;

	private Float saldo;

	private String codError;

	private String descripcionError;
}
