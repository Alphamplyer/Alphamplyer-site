package com.alphamplyer.website.websiteadministration.controllers;

import com.alphamplyer.website.websiteadministration.beans.users.User;
import com.alphamplyer.website.websiteadministration.beans.users_permissions.Role;
import com.alphamplyer.website.websiteadministration.models.users.RegisterUser;
import com.alphamplyer.website.websiteadministration.models.users.UpdateUser;
import com.alphamplyer.website.websiteadministration.proxies.MicroserviceUserProxy;
import com.alphamplyer.website.websiteadministration.proxies.MicroserviceUsersPermissionsProxy;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import utils.validation.date.DateUtils;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        try {
            roles = microserviceUsersPermissionsProxy.getRoles();
        } catch (FeignException e) {
            logger.error("Failed to load roles", e);
            roles = null;
        }

        if (users == null || roles == null) {
            return "redirect:/error";
        }

        model.addAttribute("users", users);
        model.addAttribute("roles", roles);

        return "user_section";
    }

    /**
     * Display register page
     */
    @RequestMapping(value = "/users/new", method = RequestMethod.GET)
    public String displayNewUserPage(Model model) {
        RegisterUser registerUser = new RegisterUser();
        model.addAttribute("registerUser", registerUser);
        return "user_section_add";
    }

    /**
     * Register an user and redirect him to the users page. Show error if exist
     */
    @RequestMapping(value = "/users/new", method = RequestMethod.POST)
    public ModelAndView register(@Valid @ModelAttribute RegisterUser registerUser,
                                 BindingResult result) {

        if (result.hasErrors()) {
            return new ModelAndView("register", "registerUser", registerUser);
        }

        try {
            microserviceUserProxy.registerNewUserAccount(new User(registerUser, DateUtils.isValid(registerUser.getBirthDate()).y));
        } catch (FeignException e) {
            Model model = new ExtendedModelMap();
            model.addAttribute("registerUser", registerUser);
            model.addAttribute("error", "Username or Email or both are used");
            return new ModelAndView("user_section_add", model.asMap());
        }

        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.GET)
    public String displayEditPage(Model model, @PathVariable(name = "id") Integer id) {

        User user;

        try {
            user = microserviceUserProxy.getUserWithID(id);
        } catch (FeignException e) {
            logger.error("Failed to load user data", e);
            user = null;
        }

        if (user == null)
            return "error";

        UpdateUser updateUser = new UpdateUser();

        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setUsername(user.getUsername());

        model.addAttribute("updateUser", updateUser);
        return "user_section_edit";
    }

    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editPage(@ModelAttribute @Valid UpdateUser updateUser,
                                  BindingResult result,
                                  @PathVariable(name = "id") Integer id) {

        if (result.hasErrors()) {
            return new ModelAndView("user_section_edit", "updateUser", updateUser);
        }

        User user;

        try {
            user = microserviceUserProxy.getUserWithID(id);
        } catch (FeignException e) {
            logger.error("Failed to load user");
            user = null;
        }

        if (user == null) {
            return new ModelAndView("error");
        }

        user.setUsername(updateUser.getUsername());
        user.setEmail(updateUser.getEmail());
        user.setPassword(updateUser.getPassword());


        try {
            microserviceUserProxy.updateUser(user);
        } catch (FeignException e) {
            logger.error("Failed to update user", e);
            Model model = new ExtendedModelMap();
            model.addAttribute("updateUser", updateUser);
            model.addAttribute("error", "Username or Email or both are used");
            return new ModelAndView("user_section_edit", model.asMap());
        }

        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id) {

        try {
            microserviceUserProxy.deleteUser(id);
        } catch (FeignException e) {
            logger.error("Failed to delete user", e);
            return "error";
        }

        return "redirect:/users";
    }
}
