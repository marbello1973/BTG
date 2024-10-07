package com.btgpactual.servicio.serviceinscripcion;

import com.btgpactual.model.clientes.Cliente;
import com.btgpactual.model.clientes.ClienteRepository;
import com.btgpactual.model.producto.Producto;
import com.btgpactual.model.producto.ProductoRepository;
import com.btgpactual.model.tansaccion.TipoTransaccion;
import com.btgpactual.model.tansaccion.Transaccion;
import com.btgpactual.model.tansaccion.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    private final BigDecimal MONTO_INICIAL = BigDecimal.valueOf(500000);
    private final BigDecimal MONTO_MINIMO_VINCULADO = BigDecimal.valueOf(100000);

    public String suscribirseFondo(Integer clienteId, Integer productoId){
        Cliente cl = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Producto pr = productoRepository.findProductoById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        BigDecimal sc = cl.getSaldo();
        if(sc.compareTo(MONTO_MINIMO_VINCULADO) < 0 ){
            return "No tiene saldo disponible para vincularse al fondo:" + " " + pr.getNombre();
        }

        cl.setSaldo(sc.subtract(MONTO_MINIMO_VINCULADO));
        clienteRepository.save(cl);

        Transaccion tr = new Transaccion();
        tr.setCliente(cl);
        tr.setProducto(pr);
        tr.setTipoTransaccion(TipoTransaccion.APERTURA);
        tr.setMonto(MONTO_MINIMO_VINCULADO);
        tr.setFechaTransaccion(LocalDateTime.now());
        transaccionRepository.save(tr);
        return "Suscripcion realizada con exito al fondo " + pr.getNombre() + " exitosa";
    }

    public String cancelarSuscripcionFondo(Integer clienteId, Integer productoId){

        Cliente cl = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Producto pr = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        cl.setSaldo(cl.getSaldo().add(MONTO_MINIMO_VINCULADO));
        clienteRepository.save(cl);

        Transaccion tr = new Transaccion();
        tr.setCliente(cl);
        tr.setProducto(pr);
        tr.setTipoTransaccion(TipoTransaccion.CANCELACION);
        tr.setMonto(MONTO_MINIMO_VINCULADO);
        tr.setFechaTransaccion(LocalDateTime.now());
        transaccionRepository.save(tr);
        return "Cancelacion " + pr.getNombre() + " realizada";
    }

    public List<Transaccion> obtenerHistorialTransacciones(Integer clienteId){
        //return transaccionRepository.findByClienteId(clienteId);
        return transaccionRepository.findTransaccionesByClienteId(clienteId);
    }

    public void enviarNotificacion(Optional<Cliente> cliente, String tipoNotificacion, String mensaje) {
        if ("EMAIL".equalsIgnoreCase(tipoNotificacion)) {
            
            // Lógica para enviar email (usar alguna librería como JavaMailSender)
            System.out.println("Enviando email a " + "cliente.getEmail()" + ": " + mensaje);
        } else if ("SMS".equalsIgnoreCase(tipoNotificacion)) {
            // Lógica para enviar SMS (usar algún servicio de SMS)
            System.out.println("Enviando SMS al número " + "cliente.getTelefono()" + ": " + mensaje);
        }
    }

}
