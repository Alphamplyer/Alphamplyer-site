package com.alphamplyer.website.main.controllers;

import com.alphamplyer.website.main.beans.User;
import com.alphamplyer.website.main.models.RegisterUser;
import com.alphamplyer.website.main.proxies.MicroserviceUserProxy;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegisterController {

    private MicroserviceUserProxy microserviceUserProxy;

    @Autowired
    public void setMicroserviceUserProxy(MicroserviceUserProxy microserviceUserProxy) {
        this.microserviceUserProxy = microserviceUserProxy;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerDisplay(Model model) {
        RegisterUser registerUser = new RegisterUser();
        model.addAttribute("registerUser", registerUser);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid @ModelAttribute RegisterUser registerUser, BindingResult result) {

        if (result.hasErrors())
        {
            return new ModelAndView("register", "registerUser", registerUser);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate;

        try {
            birthDate = dateFormat.parse(registerUser.getBirthDate());
        } catch (ParseException e) {
            result.rejectValue("birthDate", "Wrong format, please use 'day/month/year'.");
            return new ModelAndView("register", "registerUser", registerUser);
        }

        try {
            microserviceUserProxy.registerNewUserAccount(new User(registerUser, birthDate));
        } catch (FeignException e) {
            System.out.println("========================================\n" + e);

            Model model = new ExtendedModelMap();
            model.addAttribute("registerUser", registerUser);
            model.addAttribute("error", "Username or Email or both are used");
            return new ModelAndView("register", model.asMap());
        }

        return new ModelAndView("redirect:login");
    }
}
