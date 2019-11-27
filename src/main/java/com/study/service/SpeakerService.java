package com.study.service;

import com.study.persistence.DTO.SpeakerDTO;
import com.study.persistence.dao.RoleDAO;
import com.study.persistence.dao.SpeakerDAO;
import com.study.persistence.entity.Speaker;
import com.study.persistence.mapper.EntityDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpeakerService implements DBActionsService{
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
                    .map(speaker -> EntityDTOMapper.mapSpeaker(speaker, roleDao.getById(speaker.getUserRole())))
                    .collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    public List<SpeakerDTO> convertEntityToDTO(List<Speaker> speakers) {
        return speakers
                .stream()
                .map(speaker -> EntityDTOMapper.mapSpeaker(speaker, roleDao.getById(speaker.getUserRole())))
                .collect(Collectors.toList());
    }

    public SpeakerDTO convertEntityToDTO(Speaker speaker){
        return EntityDTOMapper.mapSpeaker(speaker, roleDao.getById(speaker.getUserRole()));
    }

    public SpeakerDTO getById(int id){
        return convertEntityToDTO(speakerDAO.getById(id));
    }

    @Override
    public void perform(Map<String, String> params) {

    }
}
