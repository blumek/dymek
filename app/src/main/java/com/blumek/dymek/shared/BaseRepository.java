package com.blumek.dymek.shared;

public interface BaseRepository<T> {
    void add(T t);
    void update(T t);
    void delete(T t);
}
