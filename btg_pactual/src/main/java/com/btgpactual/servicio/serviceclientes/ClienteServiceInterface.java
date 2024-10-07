package com.btgpactual.servicio.serviceclientes;

import com.btgpactual.model.clientes.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteServiceInterface {
    public Cliente save(Cliente cliente);
    public Optional<Cliente> getId(Integer id);
    public void update(Cliente cliente);
    public void delete(Integer id);
    public List<Cliente> getAll(Cliente cliente);
    public Optional<Cliente> obtenerClientePorId(Integer id);
}
