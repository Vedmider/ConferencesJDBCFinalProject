package com.study.service;

import com.study.persistence.dao.ConferenceDAO;
import com.study.persistence.entity.Conference;
import com.study.persistence.mapper.EntityDTOMapper;
import com.study.web.DTO.ConferenceDTO;
import com.study.web.DTO.ReportDTO;
import com.study.web.DTO.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdministrationService implements DBActionsService {
    private static final Logger LOG = LoggerFactory.getLogger(AdministrationService.class);
    private static final ConferenceDAO conferenceDAO = new ConferenceDAO();
    private static final ReportService reportService = new ReportService();
    private static final UserService userService = new UserService();

    public List<ConferenceDTO> getAllConferences() {
        List<Conference> conferences = conferenceDAO.getAll();
        if (conferences.isEmpty()) {
            LOG.info("Get empty conferences list from database");
            return Collections.EMPTY_LIST;
        }

        return conferences.stream()
                .map(conference -> EntityDTOMapper
                        .mapConference(conference, getAllReportsById(conference.getId())))
                .collect(Collectors.toList());
    }

    public List<ReportDTO> getAllReportsById(int id) {
        return reportService.convertEntityToDTO(conferenceDAO.getAllReports(id));
    }

    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    public void deleteConference(int id) {
        LOG.info("Starting deleting conference entity");
        Conference conference = new Conference();
        conference.setId(id);
        conferenceDAO.delete(conference);
    }

    public boolean deleteConference(Conference conference) {
        LOG.info("Starting deleting conference entity");
        return conferenceDAO.delete(conference);
    }

    public boolean updateConference(Conference conference) {
        if (conference.getId() == 0) {
            LOG.error("Conference entity has ID = 0");
        }
        return conferenceDAO.update(conference);
    }

    public int createConference(Conference conference) {
        return conferenceDAO.create(conference);
    }

    @Override
    public void perform(Map<String, String> params) {
        Conference conference = mapConferenceFromParams(params);
        String type = params.get("type");

        LOG.info("DB Action type: " + type);
        if (type.equalsIgnoreCase("delete")) {
            if (conference.getId() != 0) {
                deleteConference(conference);
            }
        }
        if (type.equalsIgnoreCase("update")) {
            if (conference.getId() != 0) {
                updateConference(conference);
            }
        }
        if (type.equalsIgnoreCase("create")) {
            if (conference.getId() == 0) {
                createConference(conference);
            }
        }
    }

    private Conference mapConferenceFromParams(Map<String, String> params) {
        Conference conference;

        if (params.get("id") != null) {
            conference = conferenceDAO.getById(Integer.parseInt(params.get("id")));
        } else {
            conference = new Conference();
        }

        if (params.get("theme") != null) {
            conference.setTheme(params.get("theme"));
        }

        if (params.get("plannedDateTime") != null) {
            String[] dateTime = params.get("plannedDateTime").split("T");
            String[] date = dateTime[0].split("-");
            String[] time = dateTime[1].split(":");
            conference.setPlannedDateTime(LocalDateTime.of(Integer.parseInt(date[0]),
                    Integer.parseInt(date[1]),
                    Integer.parseInt(date[2]),
                    Integer.parseInt(time[0]),
                    Integer.parseInt(time[1]),
                    Integer.parseInt(time[2])));
        }

        if (params.get("happenedDateTime") != null) {
            String[] dateTime = params.get("happenedDateTime").split("T");
            String[] date = dateTime[0].split("-");
            String[] time = dateTime[1].split(":");
            conference.setPlannedDateTime(LocalDateTime.of(Integer.parseInt(date[0]),
                    Integer.parseInt(date[1]),
                    Integer.parseInt(date[2]),
                    Integer.parseInt(time[0]),
                    Integer.parseInt(time[1]),
                    Integer.parseInt(time[2])));
        }

        if (params.get("address") != null) {
            conference.setAddress(params.get("address"));
        }

        return conference;
    }
}
