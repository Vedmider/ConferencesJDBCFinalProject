package com.study.persistence.dao;

import com.study.persistence.entity.Conference;

import java.util.List;

public class ConferenceDAO extends AbstractDao<Conference> {
    private static final String COUNT_ROWS = "SELECT COUNT(1) FROM conferences";
    private static final String SELECT_ALL_FROM_CONFERENCES = "SELECT * FROM conferences";
    private static final String INSERT_INTO_CONFERENCES = "INSERT INTO conferences(theme, date_time_planned, date_time_happened, address) "
            + "VALUE (?,?,?,?)";

    public int count(){
        return countRows(COUNT_ROWS, preparedStatement -> {});
    }

    @Override
    public Conference getById(int id) {
        return null;
    }

    @Override
    public List<Conference> getAll() {
        return null;
    }

    @Override
    public List<Conference> getAllConditional(String query, EntityMapper<Conference> mapper) {
        return null;
    }

    @Override
    public int create(Conference entity) {
        return 0;
    }

    @Override
    public boolean update(Conference entity) {
        return false;
    }

    @Override
    public boolean delete(Conference entity) {
        return false;
    }
}
