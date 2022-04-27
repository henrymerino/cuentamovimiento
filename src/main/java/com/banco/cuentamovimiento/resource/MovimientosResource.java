package com.banco.cuentamovimiento.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.cuentamovimiento.dto.MovimientoDto;
import com.banco.cuentamovimiento.model.Cuenta;
import com.banco.cuentamovimiento.model.Movimientos;
import com.banco.cuentamovimiento.model.Persona;
import com.banco.cuentamovimiento.service.CuentaService;
import com.banco.cuentamovimiento.service.MovimientoService;
import com.banco.cuentamovimiento.service.PersonaService;
import com.banco.cuentamovimiento.util.Variables;

@RestController
@RequestMapping(path = "/movimientos")
public class MovimientosResource {

	private final MovimientoService movimientoService;

	private final PersonaService personaService;

	private final CuentaService cuentaService;

	@Autowired
	public MovimientosResource(MovimientoService movimientoService, PersonaService personaService,
			CuentaService cuentaService) {
		this.movimientoService = movimientoService;
		this.personaService = personaService;
		this.cuentaService = cuentaService;

	}

	@PostMapping("/add")
	public ResponseEntity<MovimientoDto> addMovimientos(@RequestBody MovimientoDto movimiento) {
		Persona personaBuscada = personaService.findPersona(movimiento.getIdentificacion());
		Cuenta cuentaBusqueda = cuentaService.findCuentaByNumeroCuenta(movimiento.getNumeroCuenta());
		if (personaBuscada != null && personaBuscada.getCliente().getEstado() && cuentaBusqueda != null
				&& cuentaBusqueda.getEstado()) {
			Movimientos newMovimiento = new Movimientos();
			newMovimiento.setFecha(new Date());
			newMovimiento.setCuenta(cuentaBusqueda);
			if (movimiento.getValor() > 0) {
				newMovimiento.setTipoMovimiento("Deposito " + movimiento.getValor().toString());
				Float saldoDisponible = movimientoService.findUltimoMovimiento(cuentaBusqueda.getCuentaId()).getSaldo()
						+ movimiento.getValor();
				newMovimiento.setSaldo(saldoDisponible);
				movimiento.setValor(movimiento.getValor());
				newMovimiento.setValor(movimiento.getValor());
				movimientoService.addMovimiento(newMovimiento);
				movimiento.setCodError("000");
				movimiento.setDescripcionError("Transacción exitosa");
				return new ResponseEntity<>(movimiento, HttpStatus.CONFLICT);
			} else {
				newMovimiento.setTipoMovimiento("Retiro " + Math.abs(movimiento.getValor()));
				Float saldoDisp = movimientoService.findUltimoMovimiento(cuentaBusqueda.getCuentaId()).getSaldo();
				if(saldoDisp > 0 && (saldoDisp >= Math.abs(movimiento.getValor()))) {
					List<Movimientos> lista = movimientoService.findMovimientosRetiroByFecha(new Date());
					Float sumaRetiros = 0f;
					for(Movimientos item : lista) {
						sumaRetiros = sumaRetiros + (Math.abs(item.getValor()));
						
					}
					Variables limite = Variables.LIMITE;
					
					if(sumaRetiros > limite.getValor()) {
						movimiento = new MovimientoDto();
						movimiento.setCodError("020");
						movimiento.setDescripcionError("Cupo diario Excedido: " + limite.getValor());
						return new ResponseEntity<>(movimiento, HttpStatus.CONFLICT);
					}else if(Math.abs(movimiento.getValor()) > limite.getValor()) {
						movimiento = new MovimientoDto();
						movimiento.setCodError("020");
						movimiento.setDescripcionError("El valor de retiro no puede exceder el cupo diario de retiro: " + limite.getValor());
					}
					
					Float saldoDisponible = saldoDisp - Math.abs(movimiento.getValor());
					newMovimiento.setSaldo(saldoDisponible);
					movimiento.setValor(movimiento.getValor());
					newMovimiento.setValor(movimiento.getValor());
					movimientoService.addMovimiento(newMovimiento);
					movimiento.setCodError("000");
					movimiento.setDescripcionError("Transacción exitosa");
					return new ResponseEntity<>(movimiento, HttpStatus.CREATED);
				}else {
					movimiento = new MovimientoDto();
					movimiento.setCodError("010");
					movimiento.setDescripcionError("Saldo no disponible");
					return new ResponseEntity<>(movimiento, HttpStatus.CONFLICT);
				}
				
			}
			
		} else {
			movimiento = new MovimientoDto();
			movimiento.setCodError("006");
			movimiento.setDescripcionError("No pudo realizar la transacción");
			return new ResponseEntity<>(movimiento, HttpStatus.CONFLICT);
		}

	}

}
