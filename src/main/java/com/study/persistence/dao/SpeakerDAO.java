package com.study.persistence.dao;

import com.study.persistence.entity.Speaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SpeakerDAO extends AbstractDao<Speaker> {
    private static final Logger LOG = LoggerFactory.getLogger(SpeakerDAO.class);
    private static final String SELECT_ALL_FROM_SPEAKER = "SELECT * FROM users u JOIN speaker s on u.id = s.user_id";
    private static final String SELECT_ALL_LIMITED_FROM_SPEAKER = "SELECT * FROM  users u JOIN speaker s on u.id = s.user_id LIMIT ";
    private static final String SELECT_FROM_SPEAKER = "SELECT * FROM users u JOIN speaker s on u.id = s.user_id WHERE s.user_id = ";
    private static final String INSERT_INTO_SPEAKER = "INSERT INTO speaker(rating, bonuses, user_id)  VALUE (?, ?, ?)";
    private static final String UPDATE_SPEAKER = "UPDATE speaker SET rating = ?, bonuses = ? WHERE user_id = ?";
    private static final String DELETE_SPEAKER = "DELETE FROM speaker WHERE user_id = ?";


    @Override
    public Speaker getById(int id) {
        LOG.info("Getting Speaker entity ID {}", id);
        return selectFromDB(SELECT_FROM_SPEAKER + id, resultSet -> {
            Speaker speaker = new Speaker();
            speaker.setLogin(resultSet.getString("login"));
            speaker.setPassword(resultSet.getString("user_password"));
            speaker.setFirstName(resultSet.getString("first_name"));
            speaker.setLastName(resultSet.getString("last_name"));
            speaker.setEmail(resultSet.getString("email"));
            speaker.setUserRole(resultSet.getInt("user_role"));
            speaker.setRating(resultSet.getInt("rating"));
            speaker.setBonuses(resultSet.getInt("bonuses"));
            speaker.setId(resultSet.getInt("id"));
            return speaker;
        }).get(0);
    }

    @Override
    public List<Speaker> getAll() {
        LOG.info("Getting Speaker all entity from DB");
        return selectFromDB(SELECT_ALL_FROM_SPEAKER, resultSet -> {
            Speaker speaker = new Speaker();
            speaker.setLogin(resultSet.getString("login"));
            speaker.setPassword(resultSet.getString("user_password"));
            speaker.setFirstName(resultSet.getString("first_name"));
            speaker.setLastName(resultSet.getString("last_name"));
            speaker.setEmail(resultSet.getString("email"));
            speaker.setUserRole(resultSet.getInt("user_role"));
            speaker.setRating(resultSet.getInt("rating"));
            speaker.setBonuses(resultSet.getInt("bonuses"));
            speaker.setId(resultSet.getInt("id"));
            return speaker;
        });
    }

    @Override
    public List<Speaker> getAllConditional(String query, EntityMapper<Speaker> mapper) {
        LOG.info("Getting Speaker entity conditional");
        return selectFromDB(query, mapper);
    }

    @Override
    public int create(Speaker entity) {
        LOG.info("Creating Speaker entity");
        return create(INSERT_INTO_SPEAKER, preparedStatement -> {
            preparedStatement.setInt(1, entity.getRating());
            preparedStatement.setInt(2, entity.getBonuses());
            preparedStatement.setInt(3, entity.getId());
        });
    }

    @Override
    public boolean update(Speaker entity) {
        LOG.info("Updating Speaker entity ID {}", entity.getId());
        return update(UPDATE_SPEAKER, preparedStatement -> {
            preparedStatement.setInt(1, entity.getRating());
            preparedStatement.setInt(2, entity.getBonuses());
            preparedStatement.setInt(3, entity.getId());
        });
    }

    @Override
    public boolean delete(Speaker entity) {
        LOG.info("Deleting Speaker entity ID {}", entity.getId());
        return update(DELETE_SPEAKER, preparedStatement -> {
            preparedStatement.setInt(1, entity.getId());
        });
    }

    public List<Speaker> getAll(int startPosition, int limit) {
        LOG.info("Getting Speaker all entity from DB");
        return selectFromDB(SELECT_ALL_LIMITED_FROM_SPEAKER + startPosition + ", " + limit, resultSet -> {
            Speaker speaker = new Speaker();
            speaker.setLogin(resultSet.getString("login"));
            speaker.setPassword(resultSet.getString("user_password"));
            speaker.setFirstName(resultSet.getString("first_name"));
            speaker.setLastName(resultSet.getString("last_name"));
            speaker.setEmail(resultSet.getString("email"));
            speaker.setUserRole(resultSet.getInt("user_role"));
            speaker.setRating(resultSet.getInt("rating"));
            speaker.setBonuses(resultSet.getInt("bonuses"));
            speaker.setId(resultSet.getInt("id"));
            return speaker;
        });
    }
}
