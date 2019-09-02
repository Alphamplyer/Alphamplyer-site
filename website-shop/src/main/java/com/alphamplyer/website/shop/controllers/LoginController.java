package com.alphamplyer.website.shop.controllers;

import com.alphamplyer.website.shop.beans.users.User;
import com.alphamplyer.website.shop.models.LoginUser;
import com.alphamplyer.website.shop.proxies.MicroserviceUserProxy;
import com.alphamplyer.website.shop.validation.email.Email;
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

        if (user != null) {
            if (session.getAttribute("user") != null) {
                session.removeAttribute("user");
            }
            user.setPassword(null);
            session.setAttribute("user", user);
        } else {
            Model model = new ExtendedModelMap();
            model.addAttribute("loginUser", loginUser);
            model.addAttribute("error", "Failed to login you. Anything wrong !");
            return new ModelAndView("login", model.asMap());
        }

        return new ModelAndView("redirect:index");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return "redirect:index";
    }
}
