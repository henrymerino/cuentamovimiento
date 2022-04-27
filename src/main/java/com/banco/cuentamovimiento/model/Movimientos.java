package com.banco.cuentamovimiento.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Movimientos implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "movimientos_sequence", sequenceName = "movimientos_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movimientos_sequence")
	@Column(name = "movimientos_id", updatable = false)
	private Long movimientosId;
	
    @Column(name = "FECHA")
	private Date fecha;
	
    @Column(name = "TIPOMOVIMIENTO")
	private String tipoMovimiento;
	
    @Column(name = "VALOR")
	private Float valor;
	
    @Column(name = "SALDO")
	private Float saldo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

}
