package com.alphamplyer.microservice.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.Date;

public class User {

    private Integer id;
    private Integer roleId;
    private String username;
    private String email;
    private String password;
    @JsonIgnore
    private String salt;
    private Boolean termAccepted;
    private Boolean emailValidated;
    private Date birthDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public User() { }

    public Integer getId() { return id; }
    public Integer getRoleId() { return roleId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() {
        return password;
    }
    public String getSalt() {
        return salt;
    }
    public Boolean getTermAccepted() { return termAccepted; }
    public Boolean getEmailValidated() { return emailValidated; }
    public Date getBirthDate() { return birthDate; }
    public Timestamp getCreatedAt() { return createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setId(Integer id) { this.id = id; }
    public void setRoleId(Integer roleId) { this.roleId = roleId; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }
    public void setTermAccepted(Boolean termAccepted) { this.termAccepted = termAccepted; }
    public void setEmailValidated(Boolean emailValidated) { this.emailValidated = emailValidated; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", roleId=" + roleId +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", salt='" + salt + '\'' +
            ", termAccepted=" + termAccepted +
            ", emailValidated=" + emailValidated +
            ", birthDate=" + birthDate +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}
