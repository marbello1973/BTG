package com.btgpactual.controller.sucursalcontroller;

import com.btgpactual.model.sucursal.Sucursal;
import com.btgpactual.servicio.sucursal.SucursalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {

    @Autowired
    private SucursalServiceImpl service;

    @PostMapping
    public ResponseEntity<Sucursal> save(
            @RequestBody Sucursal s,
            @RequestParam Integer clienteId
    ){
        return ResponseEntity.ok(service.save(s,clienteId));
    }

    @GetMapping
    public ResponseEntity<List<Sucursal>> findAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Sucursal>> getById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(Sucursal s){
        service.update(s);
        return ResponseEntity.ok("ok");
    }

    public ResponseEntity<String> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok("ok");
    }

}
