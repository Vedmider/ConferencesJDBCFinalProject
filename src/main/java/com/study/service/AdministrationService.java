package com.study.service;

import com.study.persistence.dao.ConferenceDAO;
import com.study.persistence.entity.Conference;
import com.study.persistence.mapper.EntityDTOMapper;
import com.study.web.DTO.ConferenceDTO;
import com.study.web.DTO.ReportDTO;
import com.study.web.DTO.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
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
        LOG.info("Starting deleting conference entity with ID {}", id);
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
            } else {
                LOG.info("Conference ID 0. Could not perform delete");
            }
        }
        if (type.equalsIgnoreCase("update")) {
            if (conference.getId() != 0) {
                updateConference(conference);
            } else {
                LOG.info("Conference ID 0. Could not perform update");
            }
        }
        if (type.equalsIgnoreCase("create")) {
            if (conference.getId() == 0) {
                createConference(conference);
            } else {
                LOG.info("Conference ID not 0. New Report would not be created");
            }
        }
    }

    private Conference mapConferenceFromParams(Map<String, String> params) {
        Conference conference;

        if (params.get("id") != null && !params.get("id").equals("")) {
            conference = conferenceDAO.getById(Integer.parseInt(params.get("id")));
        } else {
            conference = new Conference();
        }

        if (params.get("theme") != null && !params.get("theme").equals("")) {
            try {
                conference.setTheme(java.net.URLDecoder.decode(params.get("theme"), StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                LOG.error("Encoding error", e);
            }
        }

        if (params.get("plannedDate") != null && !params.get("plannedDate").equals("")) {
            String[] date = params.get("plannedDate").trim().split("\\D");
            String[] time = null;
            if (params.get("plannedTime") != null && !params.get("plannedTime").equals("")) {
                time = getTimeFromParameter(params.get("plannedTime"));
            } else {
                time = new String[]{"00", "00"};
            }

            conference.setPlannedDateTime(LocalDateTime.of(Integer.parseInt(date[2]),
                    Integer.parseInt(date[1]),
                    Integer.parseInt(date[0]),
                    Integer.parseInt(time[0]),
                    Integer.parseInt(time[1])));
        }

        if (params.get("happenedDate") != null && !params.get("happenedDate").equals("")) {
            String[] date = params.get("happenedDate").trim().split("\\D");
            String[] time = null;
            if (params.get("happenedTime") != null && !params.get("happenedTime").equals("")) {
                time = getTimeFromParameter(params.get("happenedTime"));
            } else {
                time = new String[]{"00", "00"};
            }

            conference.setPlannedDateTime(LocalDateTime.of(Integer.parseInt(date[2]),
                    Integer.parseInt(date[1]),
                    Integer.parseInt(date[0]),
                    Integer.parseInt(time[0]),
                    Integer.parseInt(time[1])));
        }

        if (params.get("address") != null && !params.get("address").equals("")) {
            conference.setAddress(params.get("address"));
        }

        return conference;
    }

    private String[] getTimeFromParameter(String timeParameter) {
        String[] time;
        if (timeParameter.contains("AM") || timeParameter.contains("PM")) {
            SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");
            try {
                time = date24Format.format(date12Format.parse(timeParameter)).trim().split(":");
            } catch (ParseException e) {
                time = new String[]{"00", "00"};
                LOG.error("Could not parse time parameter", e);
            }
            return time;
        }
        time = timeParameter.trim().split("\\D");
        return time;
    }

    public List<ConferenceDTO> getAllConferences(int startPosition, int limit) {
        List<Conference> conferences = conferenceDAO.getAll(startPosition, limit);
        if (conferences.isEmpty()) {
            LOG.info("Get empty conferences list from database");
            return Collections.EMPTY_LIST;
        }

        return conferences.stream()
                .map(conference -> EntityDTOMapper
                        .mapConference(conference, getAllReportsById(conference.getId())))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsers(int startPosition, int limit) {
        return userService.getAll(startPosition, limit);
    }
}
