package com.btgpactual.controller.inscripcioncontroller;

import com.btgpactual.model.clientes.Cliente;
import com.btgpactual.model.tansaccion.Transaccion;
import com.btgpactual.servicio.serviceclientes.ClienteServiceImpl;
import com.btgpactual.servicio.serviceinscripcion.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaccion")
public class InscripcionController {

    @Autowired
    private InscripcionService service;

    @Autowired
    private ClienteServiceImpl clienteService;

    @PostMapping("/apertura")
    public ResponseEntity<String> suscribir(@RequestParam Integer clienteId, @RequestParam Integer productoId) {
        var result = service.suscribirseFondo(clienteId, productoId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/cancelar")
    public ResponseEntity<String> cancelar(@RequestParam Integer clienteId, @RequestParam Integer productoId) {
        var result = service.cancelarSuscripcionFondo(clienteId, productoId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/historial/{id}")
    public ResponseEntity<List<Transaccion>> historial(@PathVariable Integer id){
        List<Transaccion> historial = service.obtenerHistorialTransacciones(id);
        return ResponseEntity.ok(historial);
    }

    @PostMapping("/notificacion")
    public ResponseEntity<String> enviarNotificacion(
            @RequestParam Integer clienteId,
            @RequestParam String tipoNotificacion,
            @RequestParam String mensaje
    ) {
        Optional<Cliente> cliente = clienteService.obtenerClientePorId(clienteId);
        if (cliente.isPresent()){
            service.enviarNotificacion(cliente, tipoNotificacion, mensaje);
            return ResponseEntity.ok("Mensaje enviado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
    }

}