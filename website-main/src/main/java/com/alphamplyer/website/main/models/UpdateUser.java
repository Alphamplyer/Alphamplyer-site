package com.alphamplyer.website.main.models;

import com.alphamplyer.website.main.utils.validation.email.ValidEmail;
import com.alphamplyer.website.main.utils.validation.password.PasswordMatches;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class UpdateUser implements IUserValidation {

    @NotNull
    @NotEmpty
    private String username;

    private String password;

    private String matchingPassword;

    @ValidEmail
    private String email;

    public UpdateUser() { }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getMatchingPassword() {
        return matchingPassword;
    }
    public String getEmail() {
        return email;
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
    public void setPassword(String password) {
        this.password = password;
    }
    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UpdateUser{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", matchingPassword='" + matchingPassword + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
