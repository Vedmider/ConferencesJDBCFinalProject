package com.study.service;

import com.study.persistence.DTO.ConferenceDTO;
import com.study.persistence.DTO.UserDTO;
import com.study.persistence.dao.ConferenceDAO;
import com.study.persistence.dao.ReportDAO;
import com.study.persistence.entity.Conference;
import com.study.persistence.entity.Report;
import com.study.persistence.mapper.EntityDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AdministrationService {
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
                        .mapConference(conference, reportService
                                .getAllById(conference.getId())))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsers(){
        return userService.getAll();
    }
}
