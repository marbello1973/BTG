package com.btgpactual.controller.clientecontroller;

import com.btgpactual.model.clientes.Cliente;
import com.btgpactual.servicio.serviceclientes.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl service;

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        return ResponseEntity.ok(service.save(cliente));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll (Cliente cl){
        return ResponseEntity.ok(service.getAll(cl));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> findById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Cliente cliente, @PathVariable Integer id){
        service.update(cliente);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok("Delete");
    }

    @GetMapping("/clientes-sucursales")
    public ResponseEntity<List<String>> obtenerClientesConProductosEnSucursales(){
        List<String> n = service.obtenerClientesConProductosEnSucursales();
        if(n.isEmpty()) ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok(n);
    }

    @GetMapping("/clientes-sucursales/{sucursalId}")
    public ResponseEntity<List<ClienteDTO>> clienteXsucursal(@PathVariable Integer sucursalId){
        List<ClienteDTO> cl = service.buscarClientePorSucursalId(sucursalId);
        if(cl.isEmpty()) ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok(cl);
    }

}
