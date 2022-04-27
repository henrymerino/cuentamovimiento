package com.banco.cuentamovimiento.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.cuentamovimiento.model.Cuenta;

@Repository
public interface CuentaRepositorio extends JpaRepository<Cuenta, Long>{
	
	public Cuenta findByNumeroCuenta(Long numeroCuenta);
	
}
