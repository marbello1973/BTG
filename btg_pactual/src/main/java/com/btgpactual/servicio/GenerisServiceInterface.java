package com.btgpactual.servicio;

import java.util.List;
import java.util.Optional;

public interface GenerisServiceInterface<T, ID> {
    public T save(T entity, ID id);
    public Optional<T> getId(ID id);
    public void update(T entity);
    public void delete(ID id);
    public List<T> getAll();
}
