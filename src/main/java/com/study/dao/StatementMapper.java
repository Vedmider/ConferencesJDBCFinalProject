package com.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface StatementMapper {
    void map(PreparedStatement preparedStatement) throws SQLException;
}
