package com.study.persistence.dao;

import com.study.persistence.entity.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReportDAO extends AbstractDao<Report> {
    private static final Logger LOG = LoggerFactory.getLogger(ReportDAO.class);
    private static final String SELECT_ALL_FROM_REPORTS = "SELECT * FROM reports";
    private static final String INSERT_INTO_REPORTS = "INSERT INTO reports(title, time_start, speaker_id, conference_id, registered, attended) "
           + "VALUE (?,?,?,?,?,?)";
    private static final String UPDATE_REPORTS = "";
    private static final String
    private static final String

    @Override

    public Report getById(int id) {
        return null;
    }

    @Override
    public List<Report> getAll() {
        return null;
    }

    @Override
    public List<Report> getAllConditional(String query, EntityMapper<Report> mapper) {
        return null;
    }

    @Override
    public int create(Report entity) {
        return 0;
    }

    @Override
    public boolean update(Report entity) {
        return false;
    }

    @Override
    public boolean delete(Report entity) {
        return false;
    }
}
