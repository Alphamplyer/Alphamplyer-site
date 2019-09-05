package com.alphamplyer.website.websiteadministration.models.users;

import utils.validation.date.ValidDate;
import utils.validation.email.ValidEmail;
import utils.validation.password.PasswordMatches;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@PasswordMatches
public class RegisterUser implements IUserValidation {

    @NotNull
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9 -]{2,30}")
    private String username;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @ValidDate
    private String birthDate;

    @NotNull
    @AssertTrue
    private Boolean termAccepted;

    public RegisterUser() { }

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
     * Get matching password
     * @return matching password
     */
    public String getMatchingPassword() {
        return matchingPassword;
    }

    /**
     * Get user birth date
     * @return user birth date
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Get if the user have accepted the term of use
     * @return true if term of use is accepted, false else
     */
    public Boolean getTermAccepted() {
        return termAccepted;
    }

    @Override
    public String getPasswordForValidation() {
        return password;
    }
    @Override
    public String getMatchingPasswordForValidation() {
        return matchingPassword;
    }

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
     * Define matching password
     * @param matchingPassword  matching password
     */
    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    /**
     * Define user birth date
     * @param birthDate user birth date
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Define if the user have accepted the term of use
     * @param termAccepted true if accepted, false else
     */
    public void setTermAccepted(Boolean termAccepted) {
        this.termAccepted = termAccepted;
    }
}
