package com.DevSync.Repositories;

import java.util.List;

public interface GenericRepository<T, ID> {
    T findById(ID id);
    List<T> fetchAll();
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}
