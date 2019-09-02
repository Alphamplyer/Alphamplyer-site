package com.alphamplyer.website.main.models;

public interface IUserValidation {

    /**
     * Get user password
     * @return user password
     */
    String getPasswordForValidation();

    /**
     * Get matching password
     * @return matching password
     */
    String getMatchingPasswordForValidation();
}
