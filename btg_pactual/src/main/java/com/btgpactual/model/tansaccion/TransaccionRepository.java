package com.btgpactual.model.tansaccion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
    List<Transaccion> findByClienteId(Integer clienteId);

    @Query("SELECT t FROM Transaccion t JOIN FETCH t.cliente c LEFT JOIN FETCH c.sucursales WHERE t.cliente.id = :clienteId")
    List<Transaccion> findTransaccionesByClienteId(@Param("clienteId") Integer clienteId);

}
