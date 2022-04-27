package com.banco.cuentamovimiento.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.cuentamovimiento.model.Cuenta;
import com.banco.cuentamovimiento.repositorio.CuentaRepositorio;

@Service
@Transactional
public class CuentaService {

	@Autowired
	private final CuentaRepositorio cuentaRepositorio;

	public CuentaService(CuentaRepositorio cuentaRepositorio) {
		this.cuentaRepositorio = cuentaRepositorio;
	}

	public Cuenta addCuenta(Cuenta cuenta) {
		return cuentaRepositorio.save(cuenta);
	}

	public Cuenta findCuentaByNumeroCuenta(Long numeroCuenta) {
		return cuentaRepositorio.findByNumeroCuenta(numeroCuenta);
	}
}
