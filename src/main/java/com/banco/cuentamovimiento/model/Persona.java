package com.banco.cuentamovimiento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "persona_sequence", sequenceName = "persona_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_sequence")
	@Column(name = "persona_id", updatable = false)
	private Long personaId;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "GENERO")
	private String genero;

	@Column(name = "EDAD")
	private Integer edad;

	@Column(name = "IDENTIFICACION",unique = true)
	private String identificacion;

	@Column(name = "DIRECCION")
	private String direccion;

	@Column(name = "TELEFONO")
	private String telefono;
	
//	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Persona() {
		super();

	}

	public Persona(Long personaId, String nombre, String genero, Integer edad, String identificacion, String direccion,
			String telefono) {
		super();
		this.personaId = personaId;
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Persona(String nombre, String genero, Integer edad, String identificacion, String direccion,
			String telefono) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Persona(String nombre, String genero, Integer edad, String identificacion, String direccion, String telefono,
			Cliente cliente) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cliente = cliente;
	}

}
