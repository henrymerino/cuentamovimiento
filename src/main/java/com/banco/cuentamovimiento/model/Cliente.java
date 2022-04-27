package com.banco.cuentamovimiento.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "cliente_sequence", sequenceName = "cliente_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_sequence")
	@Column(name = "cliente_id", nullable = false, updatable = false)
	private Long clienteId;
	
	@Column(name = "CONTRASENIA")
	private String contrasenia; 
	
	@Column(name = "ESTADO")
	private Boolean estado;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private List<Persona> listaPersona;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private List<Cuenta> listaCuentas;

	public Cliente() {
		super();
		
	}
	
	public Cliente(String contrasenia, Boolean estado) {
		super();
		this.contrasenia = contrasenia;
		this.estado = estado;
	}


	
		
	

}
