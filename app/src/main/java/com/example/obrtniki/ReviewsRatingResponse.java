package com.example.obrtniki;

import java.io.Serializable;

public class ReviewsRatingResponse implements Serializable {
    private int id;
    private int rating;
    private String created_at;
    private String updated_at;
    private int user_id;
    private int craftsman_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCraftsman_id() {
        return craftsman_id;
    }

    public void setCraftsman_id(int craftsman_id) {
        this.craftsman_id = craftsman_id;
    }
}
