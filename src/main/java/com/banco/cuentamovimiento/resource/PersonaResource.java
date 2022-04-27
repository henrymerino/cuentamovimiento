package com.banco.cuentamovimiento.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.cuentamovimiento.model.Persona;
import com.banco.cuentamovimiento.service.PersonaService;

@RestController
@RequestMapping(path = "/persona")
public class PersonaResource {

	private final PersonaService personaService;

	@Autowired
	public PersonaResource(PersonaService personaService) {
		this.personaService = personaService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Persona>> getAllEmployees() {
		List<Persona> personas = personaService.listarPersonas();
		return new ResponseEntity<>(personas, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Persona> addPersona(@RequestBody Persona persona) {
		Persona newPersona = personaService.addPersona(persona);
		return new ResponseEntity<>(newPersona, HttpStatus.CREATED);

	}

}
