package com.alphamplyer.website.websiteadministration.controllers;

import com.alphamplyer.website.websiteadministration.beans.users.User;
import com.alphamplyer.website.websiteadministration.models.users.LoginUser;
import com.alphamplyer.website.websiteadministration.proxies.MicroserviceUserProxy;
import utils.validation.email.Email;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    private MicroserviceUserProxy microserviceUserProxy;

    @Autowired
    public void setMicroserviceUserProxy(MicroserviceUserProxy microserviceUserProxy) {
        this.microserviceUserProxy = microserviceUserProxy;
    }

    /**
     * Display login page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginDisplay(Model model) {
        LoginUser loginUser = new LoginUser();
        model.addAttribute("loginUser", loginUser);
        return "login";
    }

    /**
     * try to login user. Display the login page with error if we failed to login user. Redirect him to index page if success
     * @param loginUser user data
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@Valid @ModelAttribute LoginUser loginUser, BindingResult result, HttpSession session) {

        if (result.hasErrors())
            return new ModelAndView("login", "loginUser", loginUser);

        User user;

        try {
            user = microserviceUserProxy.login(loginUser.getIdentifier(), loginUser.getPassword(), Email.isValid(loginUser.getIdentifier()));
        } catch (FeignException e) {
            user = null;
        }

        if (user != null && (user.getRoleId() > 1 || user.getRoleId() == -1)) {
            if (session.getAttribute("user") != null) {
                session.removeAttribute("user");
            }
            user.setPassword(null);
            session.setAttribute("user", user);
            return new ModelAndView("redirect:index");
        }

        Model model = new ExtendedModelMap();
        model.addAttribute("loginUser", loginUser);

        if (user != null) {
            model.addAttribute("error", "You don't have the require permission to log in here ");
        }
        else {
            model.addAttribute("error", "Failed to log in you. Anything wrong !");
        }

        return new ModelAndView("login", model.asMap());
    }

    /**
     * Log out the user and redirect him to index page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return "redirect:index";
    }
}