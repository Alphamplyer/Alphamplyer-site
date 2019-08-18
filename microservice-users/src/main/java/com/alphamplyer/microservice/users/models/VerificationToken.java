package com.alphamplyer.microservice.users.models;

import java.sql.Timestamp;
import java.util.Calendar;

public class VerificationToken {

    private long id;
    private String token;
    private Integer userID;
    private Timestamp expiryDateTime;

    public VerificationToken() { }

    public long getId() {
        return id;
    }
    public String getToken() {
        return token;
    }
    public Integer getUserID() {
        return userID;
    }
    public Timestamp getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    public void setExpiryDateTime(Timestamp expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }

    @Override
    public String toString() {
        return "VerificationToken{" +
            "id=" + id +
            ", token='" + token + '\'' +
            ", userID=" + userID +
            ", expiryDateTime=" + expiryDateTime +
            '}';
    }
}
