package com.banco.cuentamovimiento.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.cuentamovimiento.model.Persona;
import com.banco.cuentamovimiento.repositorio.PersonaRepositorio;

@Service("personaService")
@Transactional
public class PersonaService {
	

	private PersonaRepositorio personaRepositorio;
	
	@Autowired
	public PersonaService(PersonaRepositorio personaRepositorio) {
		this.personaRepositorio = personaRepositorio;
	} 

	public List<Persona> listarPersonas() {
	   return personaRepositorio.findAll();
	
	}
	
	public Persona addPersona(Persona persona) {
		return personaRepositorio.save(persona);
	}
	
	public Persona findPersona (String identificacion) {
		return personaRepositorio.findByIdentificacion(identificacion);
	}
	

}
