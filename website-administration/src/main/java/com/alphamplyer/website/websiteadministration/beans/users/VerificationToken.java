package com.alphamplyer.website.websiteadministration.beans.users;

import java.sql.Timestamp;

public class VerificationToken {

    private long id;
    private String token;
    private Integer userID;
    private Timestamp expiryDateTime;

    public VerificationToken() { }

    /**
     * Get verification token ID
     * @return verification token ID
     */
    public long getId() {
        return id;
    }

    /**
     * Get verification token token
     * @return verification token token
     */
    public String getToken() {
        return token;
    }

    /**
     * Get verification token user Id
     * @return verification token user ID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * Get verification token expiry date time
     * @return verification token expiry date time
     */
    public Timestamp getExpiryDateTime() {
        return expiryDateTime;
    }


    /**
     * Define verification token Id
     * @param id verification token ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Define verification token token
     * @param token verification token token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Define verification token user ID
     * @param userID verification token user ID
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * Define verification token expiry date time
     * @param expiryDateTime verification token expiry date time
     */
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
