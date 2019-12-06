package com.study.persistence.dao;

import com.study.persistence.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserDAO extends AbstractDao<User> {
    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);
    private static final String SELECT_ALL_FROM_USERS = "SELECT * FROM users";
    private static final String SELECT_ALL_LIMITED_FROM_USERS = "SELECT * FROM users LIMIT ";
    private static final String INSERT_INTO_USERS = "INSERT INTO users( login, user_password, first_name, last_name, email, user_role) " +
            "VALUE (?, ?, ?, ?, ?,?) ";
    private static final String UPDATE_USERS = "UPDATE users SET login = ?, user_password = ?, first_name = ?, last_name = ?, email = ?, user_role = ? " +
            "WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String SELECT_FROM_USERS = "SELECT * FROM users WHERE id = ";

    @Override
    public User getById(int id) {
        LOG.info("Getting User entity ID {}", id);
        return selectFromDB(SELECT_FROM_USERS + id, resultSet -> {
            User user = new User();
            user.setId(id);
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("user_password"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setUserRole(resultSet.getInt("user_role"));
            return user;
        }).get(0);
    }

    @Override
    public List<User> getAll() {
        LOG.info("Getting User all entity from DB");
        return selectFromDB(SELECT_ALL_FROM_USERS, resultSet -> {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("user_password"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setUserRole(resultSet.getInt("user_role"));
            return user;
        });
    }

    @Override
    public List<User> getAllConditional(String query, EntityMapper<User> mapper) {
        LOG.info("Getting User entity conditional");
        return selectFromDB(query, mapper);
    }

    @Override
    public int create(User entity) {
        LOG.info("Creating User entity");
        return create(INSERT_INTO_USERS, preparedStatement -> {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getLastName());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setInt(6, entity.getUserRole());
        });
    }

    @Override
    public boolean update(User entity) {
        LOG.info("Updating User entity ID {}", entity.getId());
        return update(UPDATE_USERS, preparedStatement -> {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getLastName());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setInt(6, entity.getUserRole());
            preparedStatement.setInt(7, entity.getId());
        });
    }

    @Override
    public boolean delete(User entity) {
        LOG.info("Deleting User entity ID {}", entity.getId());
        return update(DELETE_USER, preparedStatement -> {
            preparedStatement.setInt(1, entity.getId());
        });
    }

    public List<User> getAll(int startPosition, int limit) {
        LOG.info("Getting User all entity from DB");
        return selectFromDB(SELECT_ALL_LIMITED_FROM_USERS + startPosition + ", " + limit, resultSet -> {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("user_password"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setUserRole(resultSet.getInt("user_role"));
            return user;
        });
    }
}
