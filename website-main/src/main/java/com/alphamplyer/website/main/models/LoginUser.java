package com.alphamplyer.website.main.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginUser {

    @NotNull
    @NotEmpty
    private String identifier;

    @NotNull
    @NotEmpty
    private String password;

    public LoginUser() { }

    public String getIdentifier() {
        return identifier;
    }
    public String getPassword() {
        return password;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
