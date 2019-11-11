package com.study.persistence.dao;

import com.study.persistence.entity.Right;
import com.study.persistence.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RoleDAO extends AbstractDao<Role> {
    private static final Logger LOG = LoggerFactory.getLogger(RoleDAO.class);
    private static final String SELECT_ALL_FROM_USER_ROLE = "SELECT * FROM user_role";
    private static final String INSERT_INTO_USER_ROLE = "INSERT INTO user_role(role_name) VALUE (?)";
    private static final String UPDATE_USER_ROLE = "UPDATE user_role SET role_name = ? WHERE id = ?";
    private static final String DELETE_USER_ROLE = "DELETE FROM user_role WHERE id = ?";
    private static final String SELECT_USER_ROLE = "SELECT * from user_role WHERE id = ";
    private static final String SELECT_USER_ROLE_RIGHTS = "SELECT r.id as id, r.right_title as title " +
            "FROM role_rigths_relation as relation " +
            "JOIN rights r on relation.right_id = r.id " +
            "WHERE relation.right_id = ";
    private RightDAO rightDAO = new RightDAO();
    
    @Override
    public Role getById(int id) {
        LOG.info("Getting entity Role by ID {}", id);
        return selectFromDB(SELECT_USER_ROLE + id, resultSet -> {
            Role role = new Role();
            role.setId(id);
            role.setTitle(resultSet.getString("role_name"));
            return role;
        }).get(0);
    }

    @Override
    public List<Role> getAll() {
        LOG.info("Getting all Role entities from DB");
        return selectFromDB(SELECT_ALL_FROM_USER_ROLE, resultSet -> {
            Role role = new Role();
            role.setId(resultSet.getInt("id"));
            role.setTitle(resultSet.getString("role_name"));
            return role;
        });
    }

    @Override
    public List<Role> getAllConditional(String query, EntityMapper<Role> mapper) {
        LOG.info("Perform conditional select Role entity from DB");
        return selectFromDB(query, mapper);
    }

    @Override
    public int create(Role entity) {
        LOG.info("Trying to insert Role entity {} into DB ", entity);
        return create(INSERT_INTO_USER_ROLE, preparedStatement -> {
            preparedStatement.setString(1, entity.getTitle());
        });
    }

    @Override
    public boolean update(Role entity) {
        LOG.info("Trying to update Role ID {}", entity.getId());
        return update(UPDATE_USER_ROLE, preparedStatement -> {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getId());
        });
    }

    @Override
    public boolean delete(Role entity) {
        LOG.info("Trying to delete Role entity ID {}", entity.getId());
        return update(DELETE_USER_ROLE, preparedStatement -> {
            preparedStatement.setInt(1, entity.getId());
        });
    }

    public List<Right> getRoleRights(int id){
        return  rightDAO.getAllConditional(SELECT_USER_ROLE_RIGHTS + id, resultSet -> {
           Right right = new Right();
           right.setId(resultSet.getInt("id"));
           right.setTitle(resultSet.getString("title"));
           return right;
        });
    }
}
