package com.example.duoihinhbatchu_v2.models;

import java.io.Serializable;

public class Account implements Serializable {
    private  String id, username, phone, email;

    public Account() {
    }

    public Account(String id, String username, String phone, String email) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
