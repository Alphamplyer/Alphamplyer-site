package com.alphamplyer.website.administration.proxies;

import com.alphamplyer.website.administration.beans.users.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-users", url = "localhost:10101")
public interface MicroserviceUserProxy {

    /**
     * Get user by its Id
     * @param id user ID
     * @return user or a BadRequestException/NotFoundException error
     */
    @GetMapping(value = "/users/{id}")
    User getUserWithID(@PathVariable(name = "id") Integer id);

    /**
     * Get user by its username
     * @param username user username
     * @return user or a BadRequestException/NotFoundException error
     */
    @GetMapping(value = "/users", params = "username")
    User getUserWithUsername(@RequestParam("username") String username);

    /**
     * Get user by its email
     * @param email user email
     * @return user or a BadRequestException/NotFoundException error
     */
    @GetMapping(value = "/users", params = "email")
    User getUserWithEmail(@RequestParam("email") String email);

    /**
     * Get list of user
     * @param offset from how many results does the selection start?
     * @param limit how many results are expected at the maximum?
     * @return list of user or a BadRequestException/NotFoundException error
     */
    @GetMapping(value = "/users", params = {"offset", "limit"})
    List<User> getUsers(@RequestParam(name = "offset", required = false) Integer offset,
                        @RequestParam(name = "limit", required = false) Integer limit);

    /**
     * Get list of user with the given role id
     * @param roleId user role ID
     * @param offset from how many results does the selection start?
     * @param limit how many results are expected at the maximum?
     * @return list of user or a BadRequestException/NotFoundException error
     */
    @GetMapping(value = "/users/role/{roleId}")
    List<User> getUsersByRoleId(@PathVariable(name = "roleId") Integer roleId,
                                @RequestParam(name = "offset", required = false) Integer offset,
                                @RequestParam(name = "limit", required = false) Integer limit);

    /**
     * Get list of user with ID contained in give list of ID
     * @param listIDs list of user ID
     * @return list of user or a BadRequestException/NotFoundException error
     */
    @GetMapping(value = "/users/list")
    List<User> getAuthorsByListOfIDs(@RequestParam Integer[] listIDs);

    /**
     * Login a user
     * @param identifier user identifier (email or username)
     * @param password user password
     * @param isEmail if identifier is an email
     * @return user or a FailedToLoginException error
     */
    @PostMapping(value = "/users/login")
    User login(@RequestParam(name = "identifier") String identifier,
               @RequestParam(name = "password") String password,
               @RequestParam(name = "isEmail") Boolean isEmail);

    /**
     * Register an user
     * @param user user to register
     * @return an user or a BadRequestException/UnableToInsertException error
     */
    @PostMapping(value = "/users/register")
    User registerNewUserAccount(@RequestBody User user);

    /**
     * Update an user
     * @param user user to update
     * @return HttpResponse OK or UnableToUpdateException error
     */
    @PutMapping("/users/update")
    Void updateUser(@RequestBody User user);

    /**
     * Delete an user
     * @param id user ID
     * @return HttpResponse OK or UnableToDeleteException error
     */
    @DeleteMapping("/users/{id}/delete/")
    Void deleteUser(@PathVariable(name = "id") Integer id);
}
