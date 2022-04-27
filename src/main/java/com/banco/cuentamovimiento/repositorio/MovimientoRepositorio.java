package com.banco.cuentamovimiento.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.banco.cuentamovimiento.model.Cuenta;
import com.banco.cuentamovimiento.model.Movimientos;

@Repository
public interface MovimientoRepositorio extends JpaRepository<Movimientos, Long> {

	List<Movimientos> findByCuenta(Cuenta cuenta);

	@Query(value = "select * from movimientos m where m.cuenta_id = ?1 and m.fecha = (select max(m.fecha) from movimientos m where m.cuenta_id = ?1)", nativeQuery = true)
	public Movimientos findUltimoMovimiento(Long cuentaId);

	@Query(value = "select * from movimientos m where m.tipomovimiento like 'Retiro%' and Date(?1) = (select DISTINCT(Date(m.fecha)) from movimientos m  where m.tipomovimiento like 'Retiro%')", nativeQuery = true)
	public List<Movimientos> findMovimientosRetiroByFecha(Date date);

}
