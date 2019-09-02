package com.alphamplyer.microservice.users.repositories.dao.interf;

import com.alphamplyer.microservice.users.models.User;

import java.util.List;

public interface IUserRepository {

    /**
     * Get an user by its ID
     * @param id user ID
     * @return an user or null
     */
    User getById(Integer id);

    /**
     * Get an user by its username
     * @param username user username
     * @return an user or null
     */
    User getByUsername(String username);

    /**
     * Get an user by its email
     * @param email user email
     * @return an user or null
     */
    User getByEmail(String email);

    /**
     * Count occurrence of username in database
     * @param username research username
     * @return number of occurrence of username in database
     */
    Integer countUserWithSameUsername(String username);

    /**
     * Count occurrence of email in database
     * @param email research email
     * @return number of occurrence of email in database
     */
    Integer countUserWithSameEmail(String email);

    /**
     * Get all user in range of parameters
     * @param offset from how many results does the selection start?
     * @param limit how many results are expected at the maximum?
     * @return list of user or null
     */
    List<User> getAll(Integer offset, Integer limit);

    /**
     * Get all user in range of parameters with user role ID
     * @param roleId user role ID
     * @param offset from how many results does the selection start?
     * @param limit how many results are expected at the maximum?
     * @return list of user or null
     */
    List<User> getAllByRoleID(Integer roleId, Integer offset, Integer limit);

    /**
     * Get all user where user ID is contained into the given list of ID
     * @param listIDs list of user ID
     * @return list of user or null
     */
    List<User> getAllByListIDs(List<Integer> listIDs);


    /**
     * Insert user in database
     * @param user user to insert
     * @return inserted user or null
     */
    User insert(User user);

    /**
     * Update user in database
     * @param user user to update
     */
    void update(User user);

    /**
     * Delete an user from its ID
     * @param id user ID
     */
    void delete(Integer id);
}
