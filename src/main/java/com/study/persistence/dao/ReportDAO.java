package com.study.persistence.dao;

import com.study.persistence.entity.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.util.List;

public class ReportDAO extends AbstractDao<Report> {
    private static final Logger LOG = LoggerFactory.getLogger(ReportDAO.class);
    private static final String SELECT_ALL_FROM_REPORTS = "SELECT * FROM reports";
    private static final String SELECT_ALL_LIMITED_FROM_REPORTS = "SELECT * FROM reports ORDER BY conference_id LIMIT ";
    private static final String INSERT_INTO_REPORTS = "INSERT INTO reports(title, time_start, speaker_id, conference_id, registered, attended) "
           + "VALUE (?,?,?,?,?,?)";
    private static final String UPDATE_REPORTS = "UPDATE reports SET title = ?, time_start = ?, speaker_id = ?, conference_id = ?, registered = ?, attended = ? " +
            "WHERE id = ?";
    private static final String SELECT_FROM_REPORTS = "SELECT * FROM reports WHERE id = ";
    private static final String DELETE_FROM_REPORTS = "DELETE FROM reports WHERE id = ?";


    @Override
    public Report getById(int id) {
        return selectFromDB(SELECT_FROM_REPORTS + id, resultSet -> {
            Report report = new Report();
            report.setId(id);
            report.setConferenceId(resultSet.getInt("conference_id"));
            report.setTitle(resultSet.getString("title"));
            report.setTimeStart(resultSet.getTime("time_start").toLocalTime());
            report.setSpeakerId(resultSet.getInt("speaker_id"));
            report.setAttended(resultSet.getInt("attended"));
            report.setRegistered(resultSet.getInt("registered"));
            return report;
        }).get(0);
    }

    @Override
    public List<Report> getAll() {
        return selectFromDB(SELECT_ALL_FROM_REPORTS, resultSet -> {
            Report report = new Report();
            report.setId(resultSet.getInt("id"));
            report.setConferenceId(resultSet.getInt("conference_id"));
            report.setTitle(resultSet.getString("title"));
            report.setTimeStart(resultSet.getTime("time_start").toLocalTime());
            report.setSpeakerId(resultSet.getInt("speaker_id"));
            report.setAttended(resultSet.getInt("attended"));
            report.setRegistered(resultSet.getInt("registered"));
            return report;});
    }

    @Override
    public List<Report> getAllConditional(String query, EntityMapper<Report> mapper) {
        return selectFromDB(query, mapper);
    }

    @Override
    public int create(Report entity) {
        return create(INSERT_INTO_REPORTS, preparedStatement -> {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setTime(2, Time.valueOf(entity.getTimeStart()));
            preparedStatement.setInt(3, entity.getSpeakerId());
            preparedStatement.setInt(4, entity.getConferenceId());
            preparedStatement.setInt(5, entity.getRegistered());
            preparedStatement.setInt(6, entity.getAttended());
        });
    }

    @Override
    public boolean update(Report entity) {
        return update(UPDATE_REPORTS, preparedStatement -> {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setTime(2, Time.valueOf(entity.getTimeStart()));
            preparedStatement.setInt(3, entity.getSpeakerId());
            preparedStatement.setInt(4, entity.getConferenceId());
            preparedStatement.setInt(5, entity.getRegistered());
            preparedStatement.setInt(6, entity.getAttended());
            preparedStatement.setInt(7, entity.getId());
        });
    }

    @Override
    public boolean delete(Report entity) {
        return update(DELETE_FROM_REPORTS, preparedStatement -> {
            preparedStatement.setInt(1, entity.getId());
        });
    }

    public List<Report> getAll(int startPosition, int limit) {
        return selectFromDB(SELECT_ALL_LIMITED_FROM_REPORTS + startPosition + ", " + limit, resultSet -> {
            Report report = new Report();
            report.setId(resultSet.getInt("id"));
            report.setConferenceId(resultSet.getInt("conference_id"));
            report.setTitle(resultSet.getString("title"));
            report.setTimeStart(resultSet.getTime("time_start").toLocalTime());
            report.setSpeakerId(resultSet.getInt("speaker_id"));
            report.setAttended(resultSet.getInt("attended"));
            report.setRegistered(resultSet.getInt("registered"));
            return report;});
    }
}
