package com.banco.cuentamovimiento;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.banco.cuentamovimiento.model.Cliente;
import com.banco.cuentamovimiento.repositorio.ClienteRepositorio;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ClienteRepositorioTest {
	
//	@Autowired
//	private ClienteRepositorio clienteRepositorio;

	@Test
	public void testCliente() {
		
//		Cliente cliente = clienteRepositorio.save(new Cliente("123", true));
//		assertThat(cliente.getClienteId()).isGreaterThan(0);
	}
}
