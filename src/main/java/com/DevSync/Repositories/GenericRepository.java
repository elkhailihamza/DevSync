package com.DevSync.Repositories;

import java.util.List;

public interface GenericRepository<T, ID> {
    T findById(ID id);
    List<T> fetchAll();
    T save(T entity);
    T update(T entity);
    void delete(T entity);
}
