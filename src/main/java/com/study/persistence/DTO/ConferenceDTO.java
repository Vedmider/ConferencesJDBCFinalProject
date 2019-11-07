package com.study.persistence.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class ConferenceDTO {
    private int id;
    private String theme;
    private LocalDateTime plannedDateTime;
    private LocalDateTime happenedDateTime;
    private String address;
    private List<ReportDTO> reports;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public LocalDateTime getPlannedDateTime() {
        return plannedDateTime;
    }

    public void setPlannedDateTime(LocalDateTime plannedDateTime) {
        this.plannedDateTime = plannedDateTime;
    }

    public LocalDateTime getHappenedDateTime() {
        return happenedDateTime;
    }

    public void setHappenedDateTime(LocalDateTime happenedDateTime) {
        this.happenedDateTime = happenedDateTime;
    }

    public List<ReportDTO> getReports() {
        return reports;
    }

    public void setReports(List<ReportDTO> reports) {
        this.reports = reports;
    }
}
