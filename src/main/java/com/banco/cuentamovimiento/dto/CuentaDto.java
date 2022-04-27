package com.banco.cuentamovimiento.dto;

import lombok.Data;

@Data
public class CuentaDto {

	private Long numeroCuenta;

	private String tipoCuenta;

	private Float saldoInicial;

	private Boolean estado;

	private String identificacion;

	private String codError;

	private String descripcionError;
}
