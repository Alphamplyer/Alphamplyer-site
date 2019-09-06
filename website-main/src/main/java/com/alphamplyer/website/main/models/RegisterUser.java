package com.alphamplyer.website.main.models;

import com.alphamplyer.website.main.utils.validation.email.ValidEmail;
import com.alphamplyer.website.main.utils.validation.password.PasswordMatches;

import javax.validation.constraints.*;

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
    @Pattern(regexp = "[0-3]{0,1}[0-9]{1}/[0-3]{0,1}[0-9]{1}/[0-9]{4}")
    private String birthDate;

    @NotNull
    @AssertTrue
    private Boolean termAccepted;

    public RegisterUser() { }

    public RegisterUser(@NotNull @NotEmpty @Pattern(regexp = "[a-zA-Z0-9 -]{2,30}") String username, @NotNull @NotEmpty String email, @NotNull @NotEmpty String password, @NotNull @NotEmpty String matchingPassword, @NotNull @NotEmpty @Pattern(regexp = "[0-3]{0,1}[0-9]{1}/[0-3]{0,1}[0-9]{1}/[0-9]{4}") String birthDate, @NotNull @AssertTrue Boolean termAccepted) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.birthDate = birthDate;
        this.termAccepted = termAccepted;
    }

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
