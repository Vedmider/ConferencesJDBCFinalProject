package com.study.service;

import com.study.persistence.DTO.ReportDTO;
import com.study.persistence.dao.ConferenceDAO;
import com.study.persistence.dao.ReportDAO;
import com.study.persistence.dao.SpeakerDAO;
import com.study.persistence.entity.Report;
import com.study.persistence.entity.Speaker;
import com.study.persistence.mapper.EntityDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService implements DBActionsService{
    private static final Logger LOG = LoggerFactory.getLogger(ReportService.class);
    private static final ConferenceDAO conferenceDAO = new ConferenceDAO();
    private static final SpeakerService speakerService = new SpeakerService();

    public List<ReportDTO> convertEntityToDTO(List<Report> reports) {
        return reports.stream()
                .map(report -> {
                    if (report.getSpeakerId() == 0){
                        return EntityDTOMapper.mapReport(report);
                    }
                    return EntityDTOMapper
                            .mapReport(report, speakerService.getById(report.getSpeakerId()));
                }).collect(Collectors.toList());
    }

    public List<ReportDTO> getAllById(int id){
        return convertEntityToDTO(conferenceDAO.getAllReports(id));
    }

    @Override
    public void perform(Map<String, String> params) {

    }
}
