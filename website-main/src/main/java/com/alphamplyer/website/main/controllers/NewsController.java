package com.alphamplyer.website.main.controllers;

import com.alphamplyer.website.main.beans.News;
import com.alphamplyer.website.main.beans.NewsCategory;
import com.alphamplyer.website.main.proxies.MicroserviceNewsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@Controller
public class NewsController {

    MicroserviceNewsProxy microserviceNewsProxy;

    @Autowired
    public void setMicroserviceNewsProxy(MicroserviceNewsProxy microserviceNewsProxy) {
        this.microserviceNewsProxy = microserviceNewsProxy;
    }

    @RequestMapping(value = "/news")
    public String listOfNewsDisplay(Model model) {

        List<News> newsList = null;
        List<NewsCategory> categoryFilters = null;

        try {
            newsList = microserviceNewsProxy.getNews(0, 10, true);
            categoryFilters = microserviceNewsProxy.getMainCategories();
        } catch (HttpStatusCodeException e) {
            return "error";
        }

        model.addAttribute("categoryFilters", categoryFilters);
        model.addAttribute("newsList", newsList);

        return "news";
    }

}
