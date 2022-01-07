package com.example.obrtniki;

import java.io.Serializable;

public class AddReviewResponse implements Serializable {
    private String user_id;
    private String craftsman_id;
    private String comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
