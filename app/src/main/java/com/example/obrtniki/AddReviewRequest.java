package com.example.obrtniki;

public class AddReviewRequest {
    private String user_id;
    private String craftsman_id;
    private String comment;

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
}
