package com.study.service;

import com.study.persistence.dao.RoleDAO;
import com.study.persistence.dao.SpeakerDAO;
import com.study.persistence.entity.Speaker;
import com.study.persistence.mapper.EntityDTOMapper;
import com.study.web.DTO.SpeakerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpeakerService implements DBActionsService {
    private static final Logger LOG = LoggerFactory.getLogger(SpeakerService.class);
    private static final SpeakerDAO speakerDAO = new SpeakerDAO();
    private static final RoleDAO roleDao = new RoleDAO();

    public List<SpeakerDTO> getAllSpeakers() {
        List<Speaker> speakers = speakerDAO.getAll();
        if (speakers.isEmpty()) {
            LOG.info("Get empty list from database");
        } else {
            return speakers
                    .stream()
                    .map(speaker -> convertEntityToDTO(speaker))
                    .collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }


    public SpeakerDTO convertEntityToDTO(Speaker speaker) {
        return EntityDTOMapper.mapSpeaker(speaker, roleDao.getById(speaker.getUserRole()), roleDao.getRoleRights(speaker.getUserRole()));
    }

    public boolean deleteSpeaker(Speaker speaker) {
        return speakerDAO.delete(speaker);
    }

    public int createSpeaker(Speaker speaker) {
        return speakerDAO.create(speaker);
    }

    public boolean updateSpeaker(Speaker speaker) {
        return speakerDAO.update(speaker);
    }

    public SpeakerDTO getById(int id) {
        return convertEntityToDTO(speakerDAO.getById(id));
    }

    @Override
    public void perform(Map<String, String> params) {
        String type = params.get("type");
        Speaker speaker = mapSpeakerFromParams(params);

        LOG.info("DB Action type: " + type);
        if (type.equalsIgnoreCase("delete")) {
            if (speaker.getId() != 0) {
                deleteSpeaker(speaker);
            } else {
                LOG.info("Speaker ID 0. Could not perform delete");
            }
        }
        if (type.equalsIgnoreCase("update")) {
            if (speaker.getId() != 0) {
                updateSpeaker(speaker);
            } else {
                LOG.info("Speaker ID 0. Could not perform update");
            }
        }
        if (type.equalsIgnoreCase("create")) {
            if (speaker.getId() == 0) {
                createSpeaker(speaker);
            }
        } else {
            LOG.info("Speaker ID not 0. New Report would not be created");
        }

    }


    private Speaker mapSpeakerFromParams(Map<String, String> params) {
        Speaker speaker;

        if (params.get("id") != null) {
            speaker = speakerDAO.getById(Integer.parseInt(params.get("id")));
        } else {
            speaker = new Speaker();
        }

        if (params.get("login") != null) {
            speaker.setLogin(params.get("login"));
        }
        if (params.get("password") != null) {
            speaker.setPassword(params.get("password"));
        }
        if (params.get("firstName") != null) {
            speaker.setFirstName(params.get("firstName"));
        }
        if (params.get("lastName") != null) {
            speaker.setLastName(params.get("lastName"));
        }
        if (params.get("email") != null) {
            speaker.setEmail(params.get("email"));
        }
        if (params.get("userRole") != null) {
            speaker.setUserRole(Integer
                    .parseInt(params
                            .get("userRole")));
        }
        if (params.get("rating") != null) {
            speaker.setRating(Integer
                    .parseInt(params
                            .get("rating")));
        }
        if (params.get("bonuses") != null){
            speaker.setBonuses(Integer
                    .parseInt(params
                            .get("bonuses")));
        }

        return speaker;
    }

}
