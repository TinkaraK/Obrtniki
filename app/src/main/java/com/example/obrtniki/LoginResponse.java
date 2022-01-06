package com.example.obrtniki;

import java.io.Serializable;

public class LoginResponse implements Serializable { // te podatke dobim od api
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String token;
    private int role;
    private int craftsman_id;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCraftsman_id() {
        return craftsman_id;
    }

    public void setCraftsman_id(int craftsman_id) {
        this.craftsman_id = craftsman_id;
    }
}
