package com.alphamplyer.website.websiteadministration.controllers;

import com.alphamplyer.website.websiteadministration.beans.users.User;
import com.alphamplyer.website.websiteadministration.beans.users_permissions.Role;
import com.alphamplyer.website.websiteadministration.proxies.MicroserviceUserProxy;
import com.alphamplyer.website.websiteadministration.proxies.MicroserviceUsersPermissionsProxy;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private MicroserviceUserProxy microserviceUserProxy;
    private MicroserviceUsersPermissionsProxy microserviceUsersPermissionsProxy;

    @Autowired
    public void setMicroserviceUserProxy(MicroserviceUserProxy microserviceUserProxy) {
        this.microserviceUserProxy = microserviceUserProxy;
    }

    @Autowired
    public void setMicroserviceUsersPermissionsProxy(MicroserviceUsersPermissionsProxy microserviceUsersPermissionsProxy) {
        this.microserviceUsersPermissionsProxy = microserviceUsersPermissionsProxy;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String displayUsersList(Model model) {

        List<User> users;
        List<Role> roles;

        try {
            users = microserviceUserProxy.getUsers(0, 30);
        } catch (FeignException e) {
            logger.error("Failed to load users", e);
            users = null;
        }

        if (users == null) {
            return "redirect:/error";
        }


        return "user_section";
    }
}
