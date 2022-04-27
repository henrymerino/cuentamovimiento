package com.banco.cuentamovimiento.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.cuentamovimiento.dto.CuentaDto;
import com.banco.cuentamovimiento.model.Cuenta;
import com.banco.cuentamovimiento.model.Movimientos;
import com.banco.cuentamovimiento.model.Persona;
import com.banco.cuentamovimiento.service.CuentaService;
import com.banco.cuentamovimiento.service.MovimientoService;
import com.banco.cuentamovimiento.service.PersonaService;

@RestController
@RequestMapping(path = "/cuentas")
public class CuentaResource {

	private final PersonaService personaService;

	private final CuentaService cuentaService;

	private final MovimientoService movimientoService;

	@Autowired
	public CuentaResource(PersonaService personaService, CuentaService cuentaService,
			MovimientoService movimientoService) {
		this.personaService = personaService;
		this.cuentaService = cuentaService;
		this.movimientoService = movimientoService;
	}

	@PostMapping("/add")
	public ResponseEntity<CuentaDto> addCuenta(@RequestBody CuentaDto cuenta) {
		Persona persona = personaService.findPersona(cuenta.getIdentificacion());
		if (persona != null && persona.getCliente().getEstado()) {
			Cuenta newCuenta = new Cuenta();
			cuenta.setEstado(true);
			Movimientos primerMovimiento = new Movimientos();
			newCuenta.setCliente(persona.getCliente());
			newCuenta.setEstado(true);
//			newCuenta.setNumeroCuenta((long) (Math.random() * 50 +1));
			cuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
			newCuenta.setSaldoInicial(cuenta.getSaldoInicial());
			newCuenta.setTipoCuenta(cuenta.getTipoCuenta());
			cuentaService.addCuenta(newCuenta);
			primerMovimiento.setCuenta(newCuenta);
			primerMovimiento.setFecha(new Date());
			primerMovimiento.setSaldo(cuenta.getSaldoInicial());
			primerMovimiento.setTipoMovimiento(cuenta.getTipoCuenta());
			primerMovimiento.setValor(0f);
			movimientoService.addMovimiento(primerMovimiento);
			cuenta.setCodError("000");
			cuenta.setDescripcionError("Cuenta creada exitosamente");
			return new ResponseEntity<>(cuenta, HttpStatus.CREATED);
		} else {
			cuenta = new CuentaDto();
			cuenta.setCodError("003");
			cuenta.setDescripcionError("Cuenta no pudo ser creada");
			return new ResponseEntity<>(cuenta, HttpStatus.CONFLICT);
		}

	}

	@PostMapping("/updateCuenta")
	public ResponseEntity<CuentaDto> updateCuenta(@RequestBody CuentaDto cuenta) {
		Persona persona = personaService.findPersona(cuenta.getIdentificacion());
		Cuenta cuentaBuscada = cuentaService.findCuentaByNumeroCuenta(cuenta.getNumeroCuenta());
		if (persona != null && cuentaBuscada != null && cuentaBuscada.getEstado()) {
			cuentaBuscada.setTipoCuenta(cuenta.getTipoCuenta());
			cuentaService.addCuenta(cuentaBuscada);
			List<Movimientos> listaMovimientos = movimientoService.findMovimientosByIdCuenta(cuentaBuscada);
			for (Movimientos item : listaMovimientos) {
				item.setTipoMovimiento(cuenta.getTipoCuenta());
				movimientoService.addMovimiento(item);
			}
			cuenta.setCodError("000");
			cuenta.setDescripcionError("Cuenta actualizada correctamente");
			return new ResponseEntity<>(cuenta, HttpStatus.CREATED);
		} else {
			cuenta = new CuentaDto();
			cuenta.setCodError("004");
			cuenta.setDescripcionError("Cuenta no pudo ser actualizada");
			return new ResponseEntity<>(cuenta, HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping("/delete")
	public ResponseEntity<CuentaDto> deleteCuenta(@RequestBody CuentaDto cuenta) {
		Persona persona = personaService.findPersona(cuenta.getIdentificacion());
		Cuenta cuentaBuscada = cuentaService.findCuentaByNumeroCuenta(cuenta.getNumeroCuenta());
		if (persona != null && persona.getCliente().getEstado() && cuentaBuscada != null && cuentaBuscada.getEstado()) {
			cuentaBuscada.setEstado(false);
			cuentaService.addCuenta(cuentaBuscada);
			cuenta.setCodError("005");
			cuenta.setDescripcionError("Cuneta desactivada correctamente");
			return new ResponseEntity<>(cuenta, HttpStatus.OK);
		} else {
			cuenta = new CuentaDto();
			cuenta.setCodError("005");
			cuenta.setDescripcionError("Cuneta no pudo ser desactivada");
			return new ResponseEntity<>(cuenta, HttpStatus.CONFLICT);
		}

	}

}
