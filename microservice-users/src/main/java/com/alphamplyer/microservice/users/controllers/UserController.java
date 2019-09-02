package com.alphamplyer.microservice.users.controllers;

import com.alphamplyer.microservice.users.exceptions.*;
import com.alphamplyer.microservice.users.models.User;
import com.alphamplyer.microservice.users.repositories.dao.interf.IUserRepository;
import com.alphamplyer.microservice.users.utlis.Password;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private IUserRepository userRepository;

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //region // --- < GET MAPPING > ----------------------------------------------------------------------------------- //

    /**
     * Get user by its Id
     * @param id user ID
     * @return user or a BadRequestException/NotFoundException error
     */
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUserWithID(@PathVariable(name = "id") Integer id) {

        if (id == null || id < 0)
            throw new BadRequestException("User ID is null or lower than 0");

        User user;

        try {
            user = userRepository.getById(id);
        } catch (DataAccessException e) {
            logger.error("User not found in database", e);
            throw new NotFoundException(String.format("User not found (ID = %d)", id));
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Get user by its username
     * @param username user username
     * @return user or a BadRequestException/NotFoundException error
     */
    @GetMapping(value = "/users", params = "username")
    public ResponseEntity<User> getUserWithUsername(@RequestParam("username") String username) {

        if (username == null || username.length() != 0)
            throw new BadRequestException("User username is null or lower than 0");

        User user;

        try {
            user = userRepository.getByUsername(username);
        } catch (DataAccessException e) {
            logger.error("User not found in database", e);
            throw new NotFoundException(String.format("User not found (Username = %s)", username));
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Get user by its email
     * @param email user email
     * @return user or a BadRequestException/NotFoundException error
     */
    @GetMapping(value = "/users", params = "email")
    public ResponseEntity<User> getUserWithEmail(@RequestParam("email") String email) {

        if (email == null || email.length() != 0)
            throw new BadRequestException("User email is null or lower than 0");

        User user;

        try {
            user = userRepository.getByEmail(email);
        } catch (DataAccessException e) {
            logger.error("User not found in database", e);
            throw new NotFoundException(String.format("User not found (Email = %s)", email));
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Get list of user
     * @param offset from how many results does the selection start?
     * @param limit how many results are expected at the maximum?
     * @return list of user or a BadRequestException/NotFoundException error
     */
    @GetMapping(value = "/users", params = {"offset", "limit"})
    public ResponseEntity<List<User>> getUsers(@RequestParam(name = "offset", required = false) Integer offset,
                                               @RequestParam(name = "limit", required = false) Integer limit) {

        if (offset != null && offset < 0)
            throw new BadRequestException("Offset can't be negative");
        if (limit != null && limit < 0)
            throw new BadRequestException("Limit can't be negative");

        List<User> users;

        try {
            users = userRepository.getAll(offset, limit);
        } catch (DataAccessException e) {
            logger.error("Users not found in database", e);
            throw new NotFoundException(String.format("Users not found (Offset = %d, Limit = %d) [null or 0 = No restriction, no offset, no limit]", offset, limit));
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Get list of user with the given role id
     * @param roleId user role ID
     * @param offset from how many results does the selection start?
     * @param limit how many results are expected at the maximum?
     * @return list of user or a BadRequestException/NotFoundException error
     */
    @GetMapping(value = "/users/role/{roleId}")
    public ResponseEntity<List<User>> getUsersByRoleId(@PathVariable(name = "roleId") Integer roleId,
                                                       @RequestParam(name = "offset", required = false) Integer offset,
                                                       @RequestParam(name = "limit", required = false) Integer limit) {

        if (offset != null && offset < 0)
            throw new BadRequestException("Offset can't be negative");
        if (limit != null && limit < 0)
            throw new BadRequestException("Limit can't be negative");

        List<User> users;

        try {
            users = userRepository.getAllByRoleID(roleId, offset, limit);
        } catch (DataAccessException e) {
            logger.error("Users not found in database", e);
            throw new NotFoundException(String.format("Users with roleId = %d not found (Offset = %d, Limit = %d) [null or 0 = No restriction, no offset, no limit]", roleId, offset, limit));
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Get list of user with ID contained in give list of ID
     * @param listIDs list of user ID
     * @return list of user or a BadRequestException/NotFoundException error
     */
    @GetMapping(value = "/users/list")
    public ResponseEntity<List<User>> getAuthorsByListOfIDs(@RequestParam String[] listIDs) {

        if (listIDs.length == 0)
            new ResponseEntity<>(null, HttpStatus.OK);

        List<User> users;

        int size = listIDs.length;
        Integer[] arrID = new Integer[size];

        for(int i = 0; i < size; i++) {
            arrID[i] = Integer.parseInt(listIDs[i]);
        }

        try {
            users = userRepository.getAllByListIDs(Arrays.asList(arrID));
        } catch (DataAccessException e) {
            logger.error("Users not found in database", e);
            throw new NotFoundException("Users not found");
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //endregion

    //region // --- < POST MAPPING > ---------------------------------------------------------------------------------- //

    /**
     * Login a user
     * @param identifier user identifier (email or username)
     * @param password user password
     * @param isEmail if identifier is an email
     * @return user or a FailedToLoginException error
     */
    @PostMapping(value = "/users/login")
    public ResponseEntity<User> login(@RequestParam(name = "identifier") String identifier,
                                      @RequestParam(name = "password") String password,
                                      @RequestParam(name = "isEmail") Boolean isEmail) {

        User user;

        try {
            if (isEmail) {
                user = userRepository.getByEmail(identifier);
            } else {
                user = userRepository.getByUsername(identifier);
            }
        } catch (DataAccessException e) {
            System.out.println(e);
            throw new FailedToLoginException("Failed to login.");
        }

        if (user == null)
            throw new FailedToLoginException("User does not exists !");

        if (!Password.checkPassword(password, user.getPassword(), user.getSalt()))
            throw new FailedToLoginException("Failed to login !");

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Register an user
     * @param user user to register
     * @return an user or a BadRequestException/UnableToInsertException error
     */
    @PostMapping(value = "/users/register")
    public ResponseEntity<User> registerNewUserAccount (@RequestBody User user) {

        if (userRepository.countUserWithSameUsername(user.getUsername()) > 0)
            throw new UsernameExistsException("This username is already taken");

        if (userRepository.countUserWithSameEmail(user.getEmail()) > 0)
            throw new EmailExistsException("This email is already used");

        User rUser = null;

        user.setSalt(Password.generateSalt(128));
        user.setPassword(Password.generateSecurePassword(user.getPassword(), user.getSalt()));

        try {
            rUser = userRepository.insert(user);
        } catch (DataAccessException e) {
            logger.error("Failed to insert user", e);
        }

        if (rUser == null) {
            throw new UnableToInsertException("Failed to insert user !");
        }

        return new ResponseEntity<>(rUser, HttpStatus.CREATED);
    }

    //endregion

    //region // --- < PUT MAPPING > ----------------------------------------------------------------------------------- //

    /**
     * Update an user
     * @param user user to update
     * @return HttpResponse OK or UnableToUpdateException error
     */
    @PutMapping("/users/update")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {

        try {
            userRepository.update(user);
        } catch (DataAccessException e) {
            logger.error("Failed to update user in database", e);
            throw new UnableToUpdateException("Failed to update user in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //endregion

    //region // --- < DELETE MAPPING > -------------------------------------------------------------------------------- //

    /**
     * Delete an user
     * @param id user ID
     * @return HttpResponse OK or UnableToDeleteException error
     */
    @DeleteMapping("/users/{id}/delete/")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Integer id) {

        if (id == null || id < 0)
            throw new BadRequestException("User ID is null or lower than 0");

        try {
            userRepository.delete(id);
        } catch (DataAccessException e) {
            logger.error("Failed to delete user in database", e);
            throw new UnableToDeleteException("Failed to delete user in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //endregion
}
