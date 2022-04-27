package com.banco.cuentamovimiento.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.cuentamovimiento.model.Persona;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, Long> {

	public Persona findByIdentificacion(String identificacion);

}
