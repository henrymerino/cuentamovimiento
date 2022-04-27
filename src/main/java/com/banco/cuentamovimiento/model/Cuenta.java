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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Cuenta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "cuenta_sequence", sequenceName = "cuenta_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuenta_sequence")
	@Column(name = "cuenta_id", updatable = false)
	private Long cuentaId;
	
	@Column(name = "NUMEROCUENTA")
	private Long numeroCuenta;
	
	@Column(name = "TIPOCUENTA")
	private String tipoCuenta;
	
	@Column(name = "SALDOINICIAL")
	private Float saldoInicial;
	
	@Column(name = "ESTADO")
	private Boolean estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "cuenta_id")
	private List<Movimientos> listaMovimientos;

	public Cuenta() {
		super();
		
	}

	public Cuenta(Long cuentaId, Long numeroCuenta, String tipoCuenta, Float saldoInicial, Boolean estado,
			Cliente cliente) {
		super();
		this.cuentaId = cuentaId;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
		this.cliente = cliente;
	}

	public Cuenta(Long numeroCuenta, String tipoCuenta, Float saldoInicial, Boolean estado, Cliente cliente) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
		this.cliente = cliente;
	}
	
	
	

}
