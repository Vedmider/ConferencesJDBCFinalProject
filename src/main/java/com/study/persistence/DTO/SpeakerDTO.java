package com.study.persistence.DTO;

public class SpeakerDTO extends UserDTO {
    private int rating;
    private int bonuses;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getBonuses() {
        return bonuses;
    }

    public void setBonuses(int bonuses) {
        this.bonuses = bonuses;
    }
}
