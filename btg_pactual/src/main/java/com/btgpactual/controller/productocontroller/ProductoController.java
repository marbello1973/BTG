package com.btgpactual.controller.productocontroller;

import com.btgpactual.servicio.serviceproducto.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.btgpactual.model.producto.Producto;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoServiceImpl service;

    @Autowired
    private ProductoController(ProductoServiceImpl service){
        this.service = service;
    }

    public ResponseEntity<Producto> save(
            @RequestBody Producto producto,
            @RequestParam Integer clienteId
    ){
        return ResponseEntity.ok(service.save(producto, clienteId));
    };

    @GetMapping
    public ResponseEntity<List<Producto>> findAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Producto>> findByid(@PathVariable("id") Integer productoId){
        return ResponseEntity.ok(service.getId(productoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Producto producto, @PathVariable Integer id){
        service.update(producto);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok("ok");
    }
}
