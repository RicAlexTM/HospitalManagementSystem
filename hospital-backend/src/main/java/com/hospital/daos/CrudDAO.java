package com.hospital.daos;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T, ID> {
    boolean create(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    boolean update(T entity);
    boolean delete(ID id);
}