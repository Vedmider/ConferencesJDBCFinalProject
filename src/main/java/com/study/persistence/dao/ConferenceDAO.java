package com.study.persistence.dao;

import com.study.persistence.entity.Conference;
import com.study.persistence.entity.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

public class ConferenceDAO extends AbstractDao<Conference> {
    private static final Logger LOG = LoggerFactory.getLogger(ConferenceDAO.class);
    private static final String SELECT_ALL_FROM_CONFERENCES = "SELECT * FROM conferences";
    private static final String INSERT_INTO_CONFERENCES = "INSERT INTO conferences(theme, date_time_planned, date_time_happened, address) "
            + "VALUE (?,?,?,?)";
    private static final String UPDATE_CONFERENCES = "UPDATE conferences " +
            "SET theme = ?, date_time_planned = ?, date_time_happened = ?, address = ? " +
            "WHERE id = ?";
    private static final String DELETE_FROM_CONFERENCES = "DELETE FROM conferences WHERE id = ?";
    private static final String SELECT_FROM_CONFERENCES = "SELECT * FROM conferences WHERE id = ";
    private static final String SELECT_ALL_CONFERENCE_REPORTS = "SELECT * FROM reports WHERE conference_id = ";
    private static final String SELECT_ALL_CONDITIONAL_FROM_CONFERENCES = "SELECT * FROM conferences ORDER BY id LIMIT ";
    private static final ReportDAO reportDAO = new ReportDAO();


    @Override
    public Conference getById(int id) {
        return selectFromDB(SELECT_FROM_CONFERENCES + id, resultSet -> {
            Conference conference = new Conference();
            conference.setId(id);
            conference.setTheme(resultSet.getString("theme"));
            conference.setPlannedDateTime(resultSet.getTimestamp("date_time_planned").toLocalDateTime());
            if (resultSet.getTimestamp("date_time_happened") != null) {
                conference.setHappenedDateTime(resultSet.getTimestamp("date_time_happened").toLocalDateTime());
            }
            conference.setAddress(resultSet.getString("address"));
            return conference;
        }).get(0);
    }

    @Override
    public List<Conference> getAll() {
        return selectFromDB(SELECT_ALL_FROM_CONFERENCES, resultSet -> {
            Conference conference = new Conference();
            conference.setId(resultSet.getInt("id"));
            conference.setTheme(resultSet.getString("theme"));
            conference.setPlannedDateTime(resultSet.getTimestamp("date_time_planned").toLocalDateTime());
            if (resultSet.getTimestamp("date_time_happened") != null) {
                conference.setHappenedDateTime(resultSet.getTimestamp("date_time_happened").toLocalDateTime());
            }
            conference.setAddress(resultSet.getString("address"));
            return conference;
        });
    }

    @Override
    public List<Conference> getAllConditional(String query, EntityMapper<Conference> mapper) {
        return selectFromDB(query, mapper);
    }

    @Override
    public int create(Conference entity) {
        return create(INSERT_INTO_CONFERENCES, preparedStatement -> {
            preparedStatement.setString(1, entity.getTheme());
            preparedStatement.setObject(2, entity.getPlannedDateTime());
            preparedStatement.setObject(3, entity.getHappenedDateTime());
            preparedStatement.setString(4, entity.getAddress());
        });
    }

    @Override
    public boolean update(Conference entity) {
        return update(UPDATE_CONFERENCES, preparedStatement -> {
            preparedStatement.setString(1, entity.getTheme());
            preparedStatement.setObject(2, entity.getPlannedDateTime());
            preparedStatement.setObject(3, entity.getPlannedDateTime());
            preparedStatement.setString(4, entity.getAddress());
            preparedStatement.setInt(5, entity.getId());
        });
    }

    @Override
    public boolean delete(Conference entity) {
        LOG.info("Delete parameters - entity ID: {}", entity.getId());
        boolean success = update(DELETE_FROM_CONFERENCES, preparedStatement -> {
            preparedStatement.setInt(1, entity.getId());
        });
        if (!success){
            LOG.info("Deletion by ID {} was unsuccessful", entity.getId());
        } else {
            LOG.info("Successfully deleted");
        }
        return success;
    }

    public List<Report> getAllReports(int id) {
        LOG.info("Get all reports to Conference ID: " + id);
        return reportDAO.getAllConditional(SELECT_ALL_CONFERENCE_REPORTS + id, resultSet -> {
            Report report = new Report();
            report.setId(resultSet.getInt("id"));
            report.setConferenceId(resultSet.getInt("conference_id"));
            report.setTitle(resultSet.getString("title"));
            report.setTimeStart(resultSet.getTime("time_start").toLocalTime());
            report.setSpeakerId(resultSet.getInt("speaker_id"));
            report.setAttended(resultSet.getInt("attended"));
            report.setRegistered(resultSet.getInt("registered"));
            return report;
        });
    }

    public List<Conference> getAll(int startPosition, int limit) {
        return selectFromDB(SELECT_ALL_CONDITIONAL_FROM_CONFERENCES + startPosition + ", " + limit, resultSet -> {
            Conference conference = new Conference();
            conference.setId(resultSet.getInt("id"));
            conference.setTheme(resultSet.getString("theme"));
            conference.setPlannedDateTime(resultSet.getTimestamp("date_time_planned").toLocalDateTime());
            if (resultSet.getTimestamp("date_time_happened") != null) {
                conference.setHappenedDateTime(resultSet.getTimestamp("date_time_happened").toLocalDateTime());
            }
            conference.setAddress(resultSet.getString("address"));
            return conference;
        });
    }
}
