package com.alphamplyer.website.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewsController {

    @RequestMapping(value = "/news")
    public String listOfNewsDisplay(Model model) {
        return "news";
    }

}
