package com.study.persistence.entity;

import java.time.LocalDateTime;

public class Conference {
    private int id;
    private String theme;
    private LocalDateTime plannedDateTime;
    private LocalDateTime happenedDateTime;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public LocalDateTime getHappenedDateTime() {
        return happenedDateTime;
    }

    public void setHappenedDateTime(LocalDateTime happenedDateTime) {
        this.happenedDateTime = happenedDateTime;
    }

    public LocalDateTime getPlannedDateTime() {
        return plannedDateTime;
    }

    public void setPlannedDateTime(LocalDateTime plannedDateTime) {
        this.plannedDateTime = plannedDateTime;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
