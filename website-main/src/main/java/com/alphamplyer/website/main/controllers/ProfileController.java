package com.alphamplyer.website.main.controllers;

import com.alphamplyer.website.main.beans.Role;
import com.alphamplyer.website.main.beans.User;
import com.alphamplyer.website.main.models.UpdateUser;
import com.alphamplyer.website.main.proxies.MicroserviceUserProxy;
import com.alphamplyer.website.main.proxies.MicroserviceUsersPermissionsProxy;
import feign.FeignException;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ProfileController {

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

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public String profileDisplay(Model model, @PathVariable(name = "id") Integer id) {
        User user;
        Role role;

        try {
            user = microserviceUserProxy.getUserWithID(id);
            role = microserviceUsersPermissionsProxy.getRole(user.getRoleId());
        } catch (FeignException | NullPointerException e) {
            user = null;
            role = null;
        }

        if (user == null) {
            return "error";
        }

        model.addAttribute("role", role);
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value = "/profile/{id}/edit", method = RequestMethod.GET)
    public String profileEditDisplay(Model model, @PathVariable(name = "id") Integer id, HttpSession session) {
        User user;

        try {
            user = microserviceUserProxy.getUserWithID(id);
        } catch (FeignException | NullPointerException e) {
            user = null;
        }

        if (user == null) {
            return "error";
        }

        if (session.getAttribute("user") != null) {
            User sessionUser = (User) session.getAttribute("user");

            if (!sessionUser.getId().equals(user.getId())) {
                return "error";
            }

        } else {
            return "error";
        }

        model.addAttribute("id", user.getId());
        model.addAttribute("updateUser", user.toUpdateUser());
        return "editProfile";
    }

    @RequestMapping(value = "/profile/{id}/edit", method = RequestMethod.POST)
    public ModelAndView profileEdit(@Valid @ModelAttribute UpdateUser updatedUser, BindingResult result, @PathVariable(name = "id") Integer id, HttpSession session) {
        User user;

        // Verify if user is logged and if is him
        try {
            user = microserviceUserProxy.getUserWithID(id);
        } catch (FeignException e) {
            user = null;
        }
        if (user == null) {
            return new ModelAndView("error");
        }
        if (session.getAttribute("user") != null) {
            User sessionUser = (User) session.getAttribute("user");

            if (!sessionUser.getId().equals(user.getId())) {
                return new ModelAndView("error");
            }
        }

        if (result.hasErrors()) {
            Model model = new ExtendedModelMap();
            model.addAttribute("id", user.getId());
            model.addAttribute("updateUser", updatedUser);
            return new ModelAndView("editProfile", model.asMap());
        }

        User userToUpdate = new User(updatedUser);
        userToUpdate.setId(user.getId());

        try {
            microserviceUserProxy.updateUser(userToUpdate);
        } catch (FeignException e) {
            System.out.println(e);
            Model model = new ExtendedModelMap();
            model.addAttribute("id", user.getId());
            model.addAttribute("updateUser", updatedUser);
            model.addAttribute("error", "Username or Email or both are used");
            return new ModelAndView("editProfile", model.asMap());
        }

        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }

        try {
            user = microserviceUserProxy.getUserWithID(id);
        } catch (FeignException e) {
            user = null;
        }
        if (user == null) {
            return new ModelAndView("error");
        }

        session.setAttribute("user", user);

        return new ModelAndView("redirect:/profile/"+id);
    }
}
