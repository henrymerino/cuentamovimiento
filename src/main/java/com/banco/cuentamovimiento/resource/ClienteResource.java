package com.banco.cuentamovimiento.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.cuentamovimiento.dto.ClienteDto;
import com.banco.cuentamovimiento.model.Cliente;
import com.banco.cuentamovimiento.model.Persona;
import com.banco.cuentamovimiento.service.ClienteService;
import com.banco.cuentamovimiento.service.PersonaService;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteResource {

	private final ClienteService clienteService;

	private final PersonaService personaService;

	@Autowired
	public ClienteResource(ClienteService clienteService, PersonaService personaService) {
		this.clienteService = clienteService;
		this.personaService = personaService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Cliente>> getAllClientes() {
		List<Cliente> cliente = clienteService.listarClientes();
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ClienteDto> addCliente(@RequestBody ClienteDto cliente) {
		Cliente clienteRequest = new Cliente(cliente.getContrasenia(), cliente.getEstado());
		Persona personaBuscada = personaService.findPersona(cliente.getIdentificacion());
		if(personaBuscada == null) {
			Cliente newCliente = clienteService.addCliente(clienteRequest);
			Persona persona = new Persona(cliente.getNombre(), cliente.getGenero(), cliente.getEdad(),
					cliente.getIdentificacion(), cliente.getDireccion(), cliente.getTelefono(), newCliente);
			personaService.addPersona(persona);
			cliente.setCodError("000");
			cliente.setDescripcionError("Cliente registrado correctamente");
			return new ResponseEntity<>(cliente, HttpStatus.CREATED);
		}else {
			cliente = new ClienteDto();
			cliente.setCodError("001");
			cliente.setDescripcionError("Persona ya registrada");
			return new ResponseEntity<>(cliente, HttpStatus.CONFLICT);
		}
		
	}

	@DeleteMapping("/delete/{identificacion}")
	public ResponseEntity<ClienteDto> deleteCliente(@PathVariable("identificacion") String identificacion) {
		ClienteDto clienteDto = new ClienteDto();
		Persona persona = personaService.findPersona(identificacion);
		if (persona != null) {
			Cliente cliente = clienteService.findClienteById(persona.getCliente().getClienteId());
			cliente.setEstado(false);
			clienteService.updateCliente(cliente);
			return new ResponseEntity<>(clienteDto,HttpStatus.OK);
		} else {
			clienteDto = new ClienteDto();
			clienteDto.setCodError("002");
			clienteDto.setDescripcionError("Persona no registrada");
			return new ResponseEntity<>(clienteDto,HttpStatus.CONFLICT);
		}

	}

	@PostMapping("/updateCliente")
	public ResponseEntity<ClienteDto> UpdateClieete(@RequestBody ClienteDto cliente) {
		Persona persona = personaService.findPersona(cliente.getIdentificacion());
		if (persona != null) {
			Cliente clienteBuscado = clienteService.findClienteById(persona.getCliente().getClienteId());
			clienteBuscado.setContrasenia(cliente.getContrasenia());
			persona.setDireccion(cliente.getDireccion());
			persona.setEdad(cliente.getEdad());
			persona.setGenero(cliente.getGenero());
			persona.setIdentificacion(cliente.getIdentificacion());
			persona.setNombre(cliente.getNombre());
			persona.setTelefono(cliente.getTelefono());
			clienteService.updateCliente(clienteBuscado);
			personaService.addPersona(persona);
			cliente.setCodError("000");
			cliente.setDescripcionError("Persona actualizada correctamente");
			return new ResponseEntity<>(cliente, HttpStatus.CREATED);
		} else {
			cliente = new ClienteDto();
			cliente.setCodError("002");
			cliente.setDescripcionError("Persona no pudo ser actualizado");
			return new ResponseEntity<>(cliente,HttpStatus.CONFLICT);
		}

	}
	
	
	@GetMapping("/edit/{identificacion}")
	public ResponseEntity<ClienteDto> editCliente(@PathVariable("identificacion") String identificacion) {
		ClienteDto clienteDto = new ClienteDto();
		Persona persona = personaService.findPersona(identificacion);
		if (persona != null) {
			Cliente cliente = clienteService.findClienteById(persona.getCliente().getClienteId());
			clienteDto.setContrasenia(cliente.getContrasenia());
			clienteDto.setEstado(cliente.getEstado());
			clienteDto.setDireccion(persona.getDireccion());
			clienteDto.setGenero(persona.getGenero());
			clienteDto.setNombre(persona.getNombre());
			clienteDto.setTelefono(persona.getTelefono());
			clienteDto.setIdentificacion(persona.getIdentificacion());
			clienteDto.setEdad(persona.getEdad());
			return new ResponseEntity<>(clienteDto,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(clienteDto,HttpStatus.CONFLICT);
		}

	}

}
