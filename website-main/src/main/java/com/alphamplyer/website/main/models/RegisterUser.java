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

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getMatchingPassword() {
        return matchingPassword;
    }
    public String getBirthDate() {
        return birthDate;
    }
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

    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public void setTermAccepted(Boolean termAccepted) {
        this.termAccepted = termAccepted;
    }
}
