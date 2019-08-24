package com.alphamplyer.website.main.proxies;

import com.alphamplyer.website.main.beans.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "microservice-users", url = "localhost:10101")
public interface MicroserviceUserProxy {

    @GetMapping(value = "/users/{id}")
    User getUserWithID(@PathVariable(name = "id") Integer id);

    @GetMapping(value = "/users", params = "username")
    User getUserWithUsername(@RequestParam("username") String username);

    @GetMapping(value = "/users", params = "email")
    User getUserWithEmail(@RequestParam("email") String email);

    @GetMapping(value = "/users", params = {"offset", "limit"})
    List<User> getUsers(@RequestParam(name = "offset", required = false) Integer offset,
                        @RequestParam(name = "limit", required = false) Integer limit);

    @GetMapping(value = "/users/role/{roleId}")
    List<User> getUsersByRoleId(@PathVariable(name = "roleId") Integer roleId,
                                                @RequestParam(name = "offset", required = false) Integer offset,
                                                @RequestParam(name = "limit", required = false) Integer limit);

    @GetMapping(value = "/users/list")
    List<User> getAuthorsByListOfIDs(@RequestParam Integer[] listIDs);

    @PostMapping(value = "/users/login")
    User login(@RequestParam(name = "identifier") String identifier,
               @RequestParam(name = "password") String password,
               @RequestParam(name = "isEmail") Boolean isEmail);

    @PostMapping(value = "/users/register")
    User registerNewUserAccount (@RequestBody User user);

    @PutMapping("/users/update")
    Void updateUser(@RequestBody User user);

    @DeleteMapping("/users/{id}/delete/")
    Void deleteUser(@PathVariable(name = "id") Integer id);
}
