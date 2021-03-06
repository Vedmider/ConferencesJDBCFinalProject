package com.study.service;

import com.study.persistence.dao.ReportDAO;
import com.study.persistence.entity.Report;
import com.study.persistence.mapper.EntityDTOMapper;
import com.study.web.DTO.ReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService implements DBActionsService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportService.class);
    private static final ReportDAO reportDAO = new ReportDAO();
    private static final SpeakerService speakerService = new SpeakerService();

    public List<ReportDTO> convertEntityToDTO(List<Report> reports) {
        return reports.stream()
                .map(report -> {
                    if (report.getSpeakerId() == 0) {
                        return EntityDTOMapper.mapReport(report);
                    }
                    return EntityDTOMapper
                            .mapReport(report, speakerService.getById(report.getSpeakerId()));
                }).collect(Collectors.toList());
    }

    public List<ReportDTO> getAll() {
        LOG.info("Start getting all entities");
        return convertEntityToDTO(reportDAO.getAll());
    }

    public boolean deleteReport(Report report) {
        LOG.info("Starting deleting report entity");
        return reportDAO.delete(report);
    }

    public int createReport(Report report) {
        LOG.info("Starting creating report entity");
        return reportDAO.create(report);
    }

    public boolean updateReport(Report report) {
        LOG.info("Start updating report");
        return reportDAO.update(report);
    }

    @Override
    public void perform(Map<String, String> params) {
        Report report = mapReportFromParams(params);
        String type = params.get("type");

        LOG.info("DB Action type: " + type);
        if (type.equalsIgnoreCase("delete")) {
            if (report.getId() != 0) {
                deleteReport(report);
            } else {
                LOG.info("Report ID 0. Could not perform delete");
            }
        }
        if (type.equalsIgnoreCase("update")) {
            if (report.getId() != 0) {
                updateReport(report);
            } else {
                LOG.info("Report ID 0. Could not perform update");
            }
        }
        if (type.equalsIgnoreCase("create")) {
            if (report.getId() == 0) {
                createReport(report);
            } else {
                LOG.info("Report ID not 0. New Report would not be created");
            }
        }

    }

    private Report mapReportFromParams(Map<String, String> params) {
        Report report;

        if (params.get("id") != null && !params.get("id").equals("")) {
            report = reportDAO.getById(Integer.parseInt(params.get("id")));
        } else {
            report = new Report();
        }
        if (params.get("title") != null && !params.get("title").equals("")) {
            report.setTitle(params
                    .get("title"));
        }
        if (params.get("timeStart") != null && !params.get("timeStart").equals("")) {
            String[] time = getTimeFromParameter(params.get("timeStart"));
            report.setTimeStart(LocalTime
                    .of(Integer.parseInt(time[0]), Integer.parseInt(time[1])));
        }
        if (params.get("speakerId") != null && !params.get("speakerId").equals("")) {
            report.setSpeakerId(Integer
                    .parseInt(params
                            .get("speakerId")));
        }
        if (params.get("conferenceId") != null && !params.get("conferenceId").equals("")) {
            report.setConferenceId(Integer.parseInt(params.get("conferenceId")));
        }

        if (params.get("registered") != null && !params.get("registered").equals("")) {
            report.setRegistered(Integer
                    .parseInt(params
                            .get("registered")));
        }
        if (params.get("attended") != null && !params.get("attended").equals("")) {
            report.setRegistered(Integer
                    .parseInt(params
                            .get("attended")));
        }

        return report;
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
}
