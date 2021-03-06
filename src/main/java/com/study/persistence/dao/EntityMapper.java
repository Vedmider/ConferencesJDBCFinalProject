package com.study.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface EntityMapper<T> {
    T getEntity(ResultSet resultSet) throws SQLException;
}
