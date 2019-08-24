package com.alphamplyer.website.main.controllers;

import com.alphamplyer.website.main.beans.News;
import com.alphamplyer.website.main.beans.NewsCategory;
import com.alphamplyer.website.main.models.SingleNews;
import com.alphamplyer.website.main.proxies.MicroserviceNewsProxy;
import com.alphamplyer.website.main.proxies.MicroserviceUserProxy;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Arrays;
import java.util.List;

@Controller
public class NewsController {

    private MicroserviceNewsProxy microserviceNewsProxy;
    private MicroserviceUserProxy microserviceUserProxy;

    @Autowired
    public void setMicroserviceNewsProxy(MicroserviceNewsProxy microserviceNewsProxy) {
        this.microserviceNewsProxy = microserviceNewsProxy;
    }

    @Autowired
    public void setMicroserviceUserProxy(MicroserviceUserProxy microserviceUserProxy) {
        this.microserviceUserProxy = microserviceUserProxy;
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

    @RequestMapping(value = "/news/{id}")
    public String displayNews (Model model, @PathVariable(name = "id") Integer id) {

        SingleNews singlenews = new SingleNews();

        try {
            singlenews.setNews(microserviceNewsProxy.getNewsByID(id, true));
            Integer[] listIDs = singlenews.getNews().getAuthors().toArray(new Integer[0]);
            singlenews.setAuthors(microserviceUserProxy.getAuthorsByListOfIDs(listIDs));
        } catch (FeignException e) {
            return "error";
        }

        model.addAttribute("singlenews", singlenews);
        return "singlenews";
    }

}
