package com.study.persistence.mapper;

import com.study.persistence.DTO.*;
import com.study.persistence.entity.*;

import java.util.List;

public class EntityDTOMapper {

    public static UserDTO mapUser(User userEntity, Role role) {
        UserDTO userDTO = new UserDTO();
        RoleDTO roleDTO = new RoleDTO(role);
        userDTO.setId(userEntity.getId());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRole(roleDTO);
        return userDTO;
    }

    public static SpeakerDTO mapSpeaker(Speaker speakerEntity, Role role) {
        SpeakerDTO speakerDTO = new SpeakerDTO();
        speakerDTO.setRole(new RoleDTO(role));
        speakerDTO.setBonuses(speakerEntity.getBonuses());
        speakerDTO.setRating(speakerEntity.getRating());
        speakerDTO.setEmail(speakerEntity.getEmail());
        speakerDTO.setFirstName(speakerEntity.getFirstName());
        speakerDTO.setId(speakerEntity.getId());
        speakerDTO.setLastName(speakerEntity.getLastName());
        speakerDTO.setLogin(speakerEntity.getLogin());
        speakerDTO.setPassword(speakerEntity.getPassword());
        return speakerDTO;
    }

    public static ReportDTO mapReport(Report reportEntity, SpeakerDTO speakerDTO) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(reportEntity.getId());
        reportDTO.setAttended(reportEntity.getAttended());
        reportDTO.setRegistered(reportEntity.getRegistered());
        reportDTO.setSpeaker(speakerDTO);
        reportDTO.setTimeStart(reportEntity.getTimeStart());
        reportDTO.setTitle(reportEntity.getTitle());
        return reportDTO;
    }

    public static ReportDTO mapReport(Report reportEntity){
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(reportEntity.getId());
        reportDTO.setAttended(reportEntity.getAttended());
        reportDTO.setRegistered(reportEntity.getRegistered());
        reportDTO.setTimeStart(reportEntity.getTimeStart());
        reportDTO.setTitle(reportEntity.getTitle());
        return reportDTO;
    }

    public static ConferenceDTO mapConference(Conference conferenceEntity, List<ReportDTO> reports){
        ConferenceDTO conferenceDTO = new ConferenceDTO();
        conferenceDTO.setId(conferenceEntity.getId());
        conferenceDTO.setAddress(conferenceEntity.getAddress());
        conferenceDTO.setPlannedDateTime(conferenceEntity.getPlannedDateTime());
        conferenceDTO.setHappenedDateTime(conferenceEntity.getHappenedDateTime());
        conferenceDTO.setReports(reports);
        conferenceDTO.setTheme(conferenceEntity.getTheme());
        return conferenceDTO;
    }
}
