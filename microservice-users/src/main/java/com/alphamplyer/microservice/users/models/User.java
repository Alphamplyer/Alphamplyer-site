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

    /**
     * Get user ID
     * @return user ID
     */
    public Integer getId() { return id; }

    /**
     * Get user role ID
     * @return role ID
     */
    public Integer getRoleId() { return roleId; }

    /**
     * Get user username
     * @return user username
     */
    public String getUsername() { return username; }

    /**
     * Get user email
     * @return user email
     */
    public String getEmail() { return email; }

    /**
     * Get user password
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get user salt
     * @return user salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Get if the user have accepted the term of use
     * @return true if term of use is accepted, false else
     */
    public Boolean getTermAccepted() { return termAccepted; }

    /**
     * Get if the user have validated his email
     * @return true if the user have validated his email, false else
     */
    public Boolean getEmailValidated() { return emailValidated; }

    /**
     * Get user birth date
     * @return user birth date
     */
    public Date getBirthDate() { return birthDate; }

    /**
     * Get creation date time
     * @return creation date time
     */
    public Timestamp getCreatedAt() { return createdAt; }

    /**
     * Get last update date time
     * @return last update date time
     */
    public Timestamp getUpdatedAt() { return updatedAt; }


    /**
     * Define user ID
     * @param id define user ID
     */
    public void setId(Integer id) { this.id = id; }

    /**
     * Define user role ID
     * @param roleId user role ID
     */
    public void setRoleId(Integer roleId) { this.roleId = roleId; }

    /**
     * Define user username
     * @param username user username
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Define user email
     * @param email user email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Define user password
     * @param password user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Define user salt
     * @param salt user salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Define if the user have accepted the term of use
     * @param termAccepted true if accepted, false else
     */
    public void setTermAccepted(Boolean termAccepted) { this.termAccepted = termAccepted; }

    /**
     * Define if the user have validated his email
     * @param emailValidated true if validated, false else
     */
    public void setEmailValidated(Boolean emailValidated) { this.emailValidated = emailValidated; }

    /**
     * Define user birth date
     * @param birthDate user birth date
     */
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    /**
     * Define creation date time
     * @param createdAt creation date time
     */
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    /**
     * Define last update date time
     * @param updatedAt last update date time
     */
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
