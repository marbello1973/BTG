package com.btgpactual.servicio.sucursal;


import com.btgpactual.model.sucursal.Sucursal;
import com.btgpactual.model.sucursal.SucursalRepository;
import com.btgpactual.servicio.GenerisServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalServiceImpl implements GenerisServiceInterface<Sucursal,Integer> {

    @Autowired
    private SucursalRepository repository;

    @Override
    public Sucursal save(Sucursal entity, Integer id) {
        return repository.save(entity);
    }

    @Override
    public Optional<Sucursal> getId(Integer integer) {
        Optional<Sucursal> s = repository.findById(integer);
        if(s.isEmpty()) throw new RuntimeException("be null");
        return s;
    }

    @Override
    public void update(Sucursal entity) {
        if(entity != null) throw new RuntimeException("be null");
        Optional<Sucursal> s = getId(entity.getId());

        if(s.isPresent()){
            Sucursal su = s.get();
            su.setNombre(entity.getNombre());
            su.setCiudad(entity.getCiudad());
            su.setProductos(entity.getProductos());
        }else{
            throw new RuntimeException("be null");
        }
    }

    @Override
    public void delete(Integer integer) {
        var suId = repository.findById(integer);
        if(suId.isEmpty()) throw new RuntimeException("be null");
        repository.deleteById(integer);
    }

    @Override
    public List<Sucursal> getAll() {
        List<Sucursal> all = repository.findAll();
        return all;
    }
}
