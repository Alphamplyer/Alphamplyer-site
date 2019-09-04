package com.alphamplyer.website.websiteadministration.models.users;

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


    /**
     * Get user identifier (email or username)
     * @return identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Get password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define identifier (email or username)
     * @param identifier email or username
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Define password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
