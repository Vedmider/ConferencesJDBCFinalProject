package com.study.persistence.dao;

import java.util.List;

public interface CRUDInterface<T> {

    T getById(int id);

    List<T> getAll();

    List<T> getAllConditional(String query, EntityMapper<T> mapper);

    int create(T entity);

    boolean update(T entity);

    boolean delete(T entity);
}
