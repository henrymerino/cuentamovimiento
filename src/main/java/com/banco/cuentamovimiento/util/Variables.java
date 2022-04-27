package com.banco.cuentamovimiento.util;

public enum Variables {

	LIMITE("LIMITE", 1000);

	private String nombre;
	private float valor;

	Variables(String nombre, int valor) {
		this.nombre = nombre;
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public float getValor() {
		return valor;
	}

}
