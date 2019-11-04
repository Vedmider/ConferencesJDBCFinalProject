package com.dao;

import java.util.List;

public interface CRUDInterface<T> {

    List<T> getAll();

    boolean create(T entity);

    boolean update(T entity);

    boolean delete(T entity);
}
