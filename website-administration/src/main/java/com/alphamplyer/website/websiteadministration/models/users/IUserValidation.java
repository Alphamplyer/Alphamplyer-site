package com.alphamplyer.website.websiteadministration.models.users;

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
