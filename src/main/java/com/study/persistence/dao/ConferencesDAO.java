package com.study.persistence.dao;

import com.study.persistence.entity.Conference;

import java.util.List;

public class ConferencesDAO extends AbstractDao<Conference> {
    private static final String COUNT_ROWS = "SELECT COUNT(1) FROM conferences";

    @Override
    public List<Conference> getAll() {
        return null;
    }

    @Override
    public boolean create(Conference entity) {
        return false;
    }

    @Override
    public boolean update(Conference entity) {
        return false;
    }

    @Override
    public boolean delete(Conference entity) {
        return false;
    }

    public int count(){

        return countRows(COUNT_ROWS, preparedStatement -> {});
    }
}
