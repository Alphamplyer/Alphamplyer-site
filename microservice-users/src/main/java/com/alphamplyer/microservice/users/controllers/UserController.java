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

    //endregion

    //region // --- < POST MAPPING > ---------------------------------------------------------------------------------- //

    @PostMapping(value = "users/login")
    public ResponseEntity<User> login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {

        User user;

        try {
            user = userRepository.getByEmail(email);
        } catch (DataAccessException e) {
            throw new FailedToLoginException("Failed to login !");
        }

        if (user == null)
            throw new FailedToLoginException("User does not exists !");

        if (!Password.checkPassword(password, user.getPassword(), user.getSalt()))
            throw new FailedToLoginException("Failed to login !");

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/users/register")
    public ResponseEntity<User> registerNewUserAccount (@RequestBody User user) {

        if (userRepository.countUserWithSameUsername(user.getUsername()) > 0)
            throw new UsernameExistsException("This username is already taken");

        if (userRepository.countUserWithSameEmail(user.getEmail()) > 0)
            throw new EmailExistsException("This email is already used");

        User rUser = null;

        user.setSalt(Password.generateSalt(128));

        try {
            rUser = userRepository.insert(user);
        } catch (DataAccessException e) {
            logger.error("Failed to insert user", e);
            user = null;
        }

        if (user == null) {
            throw new UnableToInsertException("Failed to insert user !");
        }

        return new ResponseEntity<>(rUser, HttpStatus.CREATED);
    }

    //endregion

    //region // --- < PUT MAPPING > ----------------------------------------------------------------------------------- //

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
