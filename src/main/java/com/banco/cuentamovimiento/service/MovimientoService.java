package com.banco.cuentamovimiento.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.cuentamovimiento.model.Cuenta;
import com.banco.cuentamovimiento.model.Movimientos;
import com.banco.cuentamovimiento.repositorio.MovimientoRepositorio;

@Service
@Transactional
public class MovimientoService {

	private final MovimientoRepositorio movimientoRepositorio;

	@Autowired
	public MovimientoService(MovimientoRepositorio movimientoRepositorio) {
		this.movimientoRepositorio = movimientoRepositorio;
	}

	public Movimientos addMovimiento(Movimientos movimiento) {
		return movimientoRepositorio.save(movimiento);
	}

	public List<Movimientos> findMovimientosByIdCuenta(Cuenta cuenta) {
		return movimientoRepositorio.findByCuenta(cuenta);
	}

	public Movimientos findUltimoMovimiento(Long cuentaId) {
		return movimientoRepositorio.findUltimoMovimiento(cuentaId);
	}

	public List<Movimientos> findMovimientosRetiroByFecha(Date date) {
		return movimientoRepositorio.findMovimientosRetiroByFecha(date);
	}

}
