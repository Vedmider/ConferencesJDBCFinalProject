package com.study.persistence.dao;

import com.study.persistence.entity.Right;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RightDAO extends AbstractDao<Right> {
    private static final Logger LOG = LoggerFactory.getLogger(Right.class);
    private static final String SELECT_ALL_FROM_RIGHTS = "SELECT * FROM rights";
    private static final String INSERT_INTO_RIGHTS = "INSERT INTO rights(right_title) VALUE (?)";
    private static final String UPDATE_RIGHTS = "UPDATE rights SET right_title = ? WHERE id = ?";
    private static final String DELETE_RIGHTS = "DELETE FROM rights WHERE id = ?";
    private static final String SELECT_RIGHTS = "SELECT * from rights WHERE id = ";

    @Override
    public Right getById(int id) {
        LOG.info("Getting entity Right by ID {}", id);
        return selectFromDB(SELECT_RIGHTS + id, resultSet -> {
            Right right = new Right();
            right.setId(id);
            right.setTitle(resultSet.getString("right_title"));
            return right;
        }).get(0);
    }

    @Override
    public List<Right> getAll() {
        LOG.info("Getting all Rights entities from DB");
        return selectFromDB(SELECT_ALL_FROM_RIGHTS, resultSet -> {
            Right right = new Right();
            right.setId(resultSet.getInt("id"));
            right.setTitle(resultSet.getString("right_title"));
            return right;
        });
    }

    @Override
    public List<Right> getAllConditional(String query, EntityMapper<Right> mapper) {
        LOG.info("Perform conditional select Right entity from DB");
        return selectFromDB(query, mapper);
    }

    @Override
    public int create(Right entity) {
        LOG.info("Trying to insert Right entity {} into DB ", entity);
        return create(INSERT_INTO_RIGHTS, preparedStatement -> {
            preparedStatement.setString(1, entity.getTitle());
        });
    }

    @Override
    public boolean update(Right entity) {
        LOG.info("Trying to update Right ID {}", entity.getId());
        return update(UPDATE_RIGHTS, preparedStatement -> {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getId());
        });
    }

    @Override
    public boolean delete(Right entity) {
        LOG.info("Trying to delete Right entity ID {}", entity.getId());
        return update(DELETE_RIGHTS, preparedStatement -> {
            preparedStatement.setInt(1, entity.getId());
        });
    }
}
