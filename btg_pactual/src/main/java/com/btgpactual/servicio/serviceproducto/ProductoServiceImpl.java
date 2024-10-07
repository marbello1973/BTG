package com.btgpactual.servicio.serviceproducto;

import com.btgpactual.model.clientes.ClienteRepository;
import com.btgpactual.model.producto.Producto;
import com.btgpactual.model.producto.ProductoRepository;
import com.btgpactual.servicio.GenerisServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements GenerisServiceInterface<Producto, Integer> {

    @Autowired
    private ProductoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Producto save(Producto entity, Integer clienteId) {
        var cl = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("be null"));
        entity.setCliente(cl);
        return repository.save(entity);
    }

    @Override
    public Optional<Producto> getId(Integer productoId) {
        Optional<Producto> producto = repository.findProductoById(productoId);
        if(producto.isEmpty()){
            throw new RuntimeException("id be null");
        }
        return producto;
    }

    @Override
    public void update(Producto entity) {
        if(entity == null) throw new RuntimeException("be null");
        Optional<Producto> pr = getId(entity.getId());
        if(pr.isPresent()){
            Producto p = pr.get();
            p.setNombre(entity.getNombre());
            p.setTipoProducto(entity.getTipoProducto());
            p.setCliente(entity.getCliente());
            p.setSucursales(entity.getSucursales());
        }else{
            throw new RuntimeException("be null");
        }
    }

    @Override
    public void delete(Integer id) {
        var prId = repository.findById(id);
        if(prId.isEmpty()) throw new RuntimeException("be null");
        repository.deleteById(id);
    }

    @Override
    public List<Producto> getAll() {
        List<Producto> productos = repository.findAll();
        for(Producto pro : productos){
            if(pro.getCliente() == null) throw new RuntimeException("be null");
        }
        return productos;
    }
}
