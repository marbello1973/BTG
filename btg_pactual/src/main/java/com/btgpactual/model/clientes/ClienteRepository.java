package com.btgpactual.model.clientes;

import com.btgpactual.controller.clientecontroller.ClienteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "SELECT DISTINCT c.nombre\n" +
            "FROM cliente c\n" +
            "JOIN producto p ON c.id = p.cliente_id\n" +
            "JOIN producto_sucursal ps ON p.id = ps.producto_id\n" +
            "JOIN sucursal s ON ps.sucursal_id = s.id\n" +
            "JOIN visitas v ON v.id_sucursal = s.id\n" +
            "WHERE v.id_cliente = c.id;", nativeQuery = true)
    List<String> findClienteConProductosEnSucursal();

    List<Cliente> findBySucursalesId(Integer sucursalId);

}


