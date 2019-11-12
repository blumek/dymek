package com.blumek.dymek.shared;

public interface BaseRepository<T> {
    void save(T t);
    void update(T t);
    void delete(T t);
}
