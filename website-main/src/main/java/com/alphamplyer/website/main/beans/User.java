package com.alphamplyer.website.main.beans;

import com.alphamplyer.website.main.models.RegisterUser;
import com.alphamplyer.website.main.models.UpdateUser;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class User {

    private Integer id;
    private Integer roleId;
    private String username;
    private String email;
    private String password;
    private Boolean termAccepted;
    private Boolean emailValidated;
    private Date birthDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public User() { }

    public User(RegisterUser registerUser, Date birthDate) {
        username = registerUser.getUsername();
        email = registerUser.getEmail();
        password = registerUser.getPassword();
        termAccepted = registerUser.getTermAccepted();
        this.birthDate = birthDate;
    }

    public User(UpdateUser updateUser) {
        username = updateUser.getUsername();
        email = updateUser.getEmail();
        password = updateUser.getPassword();
    }

    public Integer getId() { return id; }
    public Integer getRoleId() { return roleId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() {
        return password;
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
    public void setTermAccepted(Boolean termAccepted) { this.termAccepted = termAccepted; }
    public void setEmailValidated(Boolean emailValidated) { this.emailValidated = emailValidated; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public UpdateUser toUpdateUser() {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setUsername(username);
        updateUser.setEmail(email);
        return updateUser;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", roleId=" + roleId +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", termAccepted=" + termAccepted +
            ", emailValidated=" + emailValidated +
            ", birthDate=" + birthDate +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}
