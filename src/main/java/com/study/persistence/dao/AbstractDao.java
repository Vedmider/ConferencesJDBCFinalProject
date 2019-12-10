package com.study.persistence.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements CRUDInterface<T> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDao.class);

    protected static final String COLUMN_ID = "id";

    public List<T> selectFromDB(String query, EntityMapper<T> mapper) {
        List<T> result = new ArrayList<>();
        LOG.info("Select query is " + query);
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                T entity = mapper.getEntity(resultSet);

                result.add(entity);
            }

        } catch (SQLException e) {
            LOG.error("Exception while getting all entities", e);
        }

        return result;
    }


    public boolean update(String query, StatementMapper statementMapper) {
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            statementMapper.map(preparedStatement);

            int result = preparedStatement.executeUpdate();

            return result == 1;
        } catch (SQLException e) {
            LOG.error("Could not update or delete entity !!!", e);
        }

        return false;
    }


    public int create(String query, StatementMapper statementMapper) {
        int result = -1;
        try (Connection connection = DataSourceFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statementMapper.map(preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("Could not create entity!!", e);
        }
        return result;
    }

}
