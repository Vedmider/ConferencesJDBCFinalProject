package com.study.persistence.entity;

public class Speaker extends User {
    private int userId;
    private int rating;
    private int bonuses;

    public void setBonuses(int bonuses) {
        this.bonuses = bonuses;
    }

    public int getBonuses() {
        return bonuses;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
