package com.btgpactual.servicio.serviceclientes;

import com.btgpactual.controller.clientecontroller.ClienteDTO;
import com.btgpactual.model.clientes.Cliente;
import com.btgpactual.model.clientes.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteServiceInterface {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Optional<Cliente> getId(Integer id) {
        Optional<Cliente> clienteId = repository.findById(id);
        if(clienteId.isEmpty()){
            throw new RuntimeException("id be null");
        }
        return clienteId;
    }

    @Override
    public void update(Cliente cliente) {
        if(cliente != null) throw new RuntimeException("be null");
        Optional<Cliente> cl = getId(cliente.getId());
        if(cl.isPresent()){
            Cliente e = cl.get();
            e.setNombre(cliente.getNombre());
            e.setApellido(cliente.getApellido());
            e.setCiudad(cliente.getCiudad());
            e.setSucursales(cliente.getSucursales());
            e.setProductos(cliente.getProductos());
        }else{
            throw new RuntimeException("be null");
        }

    }

    @Override
    public void delete(Integer id) {
        var clId = repository.findById(id);
        if(clId.isEmpty()) throw new RuntimeException("be null");
        repository.deleteById(id);
    }

    @Override
    public List<Cliente> getAll(Cliente cliente) {
        List<Cliente> all = repository.findAll();
        all.forEach(c -> System.out.println("CLIENTES: -> " + c.getId() + " - " + c.getNombre()));
        System.out.println("TODOS LOS CLIENTES -> :" + all.toString());
        return all;
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(Integer id) {
        return repository.findById(id);
    }

    public List<String> obtenerClientesConProductosEnSucursales(){
        return  repository.findClienteConProductosEnSucursal();
    }

    public List<ClienteDTO> buscarClientePorSucursalId(Integer sucursalId){
        List<Cliente> cl = repository.findBySucursalesId(sucursalId);
        return cl.stream().map(c -> new ClienteDTO(
                c.getId(),
                c.getNombre(),
                c.getApellido(),
                c.getCiudad(),
                c.getSaldo(),
                c.getEmail(),
                c.getTelefono(),
                c.getProductos(),
                c.getSucursales()
        )).collect(Collectors.toList());
    }


}
