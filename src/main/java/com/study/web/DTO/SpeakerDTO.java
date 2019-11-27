package com.study.web.DTO;

import java.util.Objects;

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

    @Override
    public int hashCode(){
        return super.hashCode() + Objects.hash(bonuses, rating);
    }

    @Override
    public boolean equals(final Object obj){
        if(obj instanceof SpeakerDTO){
            final SpeakerDTO other = (SpeakerDTO) obj;
            return super.equals(other)
                    && bonuses == other.bonuses
                    && rating == other.rating;
        } else{
            return false;
        }
    }
}
