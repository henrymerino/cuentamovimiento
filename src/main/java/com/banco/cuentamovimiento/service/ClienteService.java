package com.banco.cuentamovimiento.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.cuentamovimiento.exception.ClienteNotFoundException;
import com.banco.cuentamovimiento.model.Cliente;
import com.banco.cuentamovimiento.repositorio.ClienteRepositorio;

@Service("clienteService")
@Transactional
public class ClienteService {

	private final ClienteRepositorio clienteRepositorio;

	@Autowired
	public ClienteService(ClienteRepositorio clienteRepositorio) {
		this.clienteRepositorio = clienteRepositorio;
	}

	public Cliente findClienteById(Long id) {
		return clienteRepositorio.findByClienteId(id).orElseThrow(() -> new ClienteNotFoundException("User by id " + id + " was not found"));
	}

	public List<Cliente> listarClientes() {
		return clienteRepositorio.findAll();

	}

	public Cliente addCliente(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}
	
	public void updateCliente(Cliente cliente) {
		clienteRepositorio.save(cliente);
		
		
	}
	
	
	
	
	
}
