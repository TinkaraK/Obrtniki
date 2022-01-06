package com.example.obrtniki;

import java.io.Serializable;

public class AddRatingResponse implements Serializable {
    private String user_id;
    private String craftsman_id;
    private String rating;
    private int id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCraftsman_id() {
        return craftsman_id;
    }

    public void setCraftsman_id(String craftsman_id) {
        this.craftsman_id = craftsman_id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
