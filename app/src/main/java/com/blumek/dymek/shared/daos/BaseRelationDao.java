package com.blumek.dymek.shared.daos;

public interface BaseRelationDao<T> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}
