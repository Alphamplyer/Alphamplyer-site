package com.alphamplyer.website.websiteadministration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * All request point on index page. For all request, user need to be logged. If he is not logged, he will be redirect to login page
 */
@Controller
public class IndexController {

    /**
     * Display index.
     */
    @RequestMapping(value = {"/", "/index", "/home"})
    public String displayIndexPage(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) { return "redirect:/login"; }

        return "index";
    }
}
