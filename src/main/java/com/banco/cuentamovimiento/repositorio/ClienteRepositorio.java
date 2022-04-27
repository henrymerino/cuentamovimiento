package com.banco.cuentamovimiento.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.cuentamovimiento.model.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
	
    Optional<Cliente> findByClienteId(Long clienteId);
}
