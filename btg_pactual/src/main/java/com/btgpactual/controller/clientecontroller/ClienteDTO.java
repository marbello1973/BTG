package com.btgpactual.controller.clientecontroller;

import com.btgpactual.model.clientes.Cliente;
import com.btgpactual.model.producto.Producto;
import com.btgpactual.model.sucursal.Sucursal;

import java.math.BigDecimal;
import java.util.List;

public record ClienteDTO(
        Integer id,
        String nombre,
        String apellido,
        String ciudad,
        BigDecimal saldo,
        String email,
        String telefono,
        List<Producto> productos,
        List<Sucursal> sucursales
) {
    public ClienteDTO(Cliente cl) {
        this(
                cl.getId(),
                cl.getNombre(),
                cl.getApellido(),
                cl.getCiudad(),
                cl.getSaldo(),
                cl.getEmail(),
                cl.getTelefono(),
                cl.getProductos(),
                cl.getSucursales()
        );

    }
}
