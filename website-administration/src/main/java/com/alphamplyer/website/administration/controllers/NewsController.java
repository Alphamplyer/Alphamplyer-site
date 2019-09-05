package com.alphamplyer.website.administration.controllers;

import com.alphamplyer.website.administration.beans.news.News;
import com.alphamplyer.website.administration.beans.users.User;
import com.alphamplyer.website.administration.models.news.FormNews;
import com.alphamplyer.website.administration.proxies.MicroserviceNewsProxy;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import utils.validation.date.TimestampUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * All request point on news actions. For all request, user need to be logged. If he is not logged, he will be redirect to login page.
 */
@Controller
public class NewsController {

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    private MicroserviceNewsProxy microserviceNewsProxy;

    @Autowired
    public void setMicroserviceNewsProxy(MicroserviceNewsProxy microserviceNewsProxy) {
        this.microserviceNewsProxy = microserviceNewsProxy;
    }

    @RequestMapping(value = "/news")
    public String displayNewsArray(Model model, HttpSession session) {
        List<News> news = null;

        try {
            news = microserviceNewsProxy.getNews(0, 30, false);
        } catch (FeignException e) {
            logger.error("failed to get news", e);
            return "error";
        }

        if (news == null) {
            logger.error("news list is empty or null");
            return "error";
        }

        model.addAttribute("news", news);

        return "news_section";
    }

    @RequestMapping(value = "/news/edit/{id}", method = RequestMethod.GET)
    public String displayNewsEditPage(@PathVariable(name = "id") Integer id, Model model, HttpSession session) {

        News db_news = null;

        try {
            db_news = microserviceNewsProxy.getNewsByID(id, false);
        } catch (FeignException e) {
            logger.error("failed to get news", e);
            return "error";
        }

        if (db_news == null) {
            logger.error("news is null");
            return "error";
        }

        FormNews news = new FormNews();
        news.setId(db_news.getId());
        news.setTitle(db_news.getTitle());
        news.setContent(db_news.getContent());
        news.setDescription(db_news.getDescription());
        news.setPublicationTime(db_news.getPublicationTime().toString());

        model.addAttribute("formNews", news);

        return "news_section_edit";
    }

    @RequestMapping(value = "/news/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editNews(@ModelAttribute("formNews") @Valid FormNews news,
                                 BindingResult result,
                                 @PathVariable(name = "id") Integer id,
                                 HttpSession session) {
        System.out.println(news.getId());

        User user = (User)session.getAttribute("user");

        if (result.hasErrors()) {
            news.setId(id);
            logger.info("has error, return on the form");
            return new ModelAndView("news_section_edit", "formNews", news);
        }

        News db_news;

        try {
            db_news = microserviceNewsProxy.getNewsByID(id, false);
        } catch (FeignException e) {
            logger.error("Failed to load news", e);
            db_news = null;
        }

        if (db_news == null) {
            logger.info("load error page");
            return new ModelAndView("error");
        }

        db_news.setId(news.getId());
        db_news.setTitle(news.getTitle());
        db_news.setDescription(news.getDescription());
        db_news.setContent(news.getContent());
        db_news.setPublicationTime(TimestampUtils.isValid(news.getPublicationTime()).y);

        try {
            microserviceNewsProxy.updateNews(db_news);
        } catch (FeignException e) {
            logger.error("Failed to update news", e);
            return new ModelAndView("error");
        }

        return new ModelAndView("redirect:/news");
    }

    @RequestMapping(value = "/news/new", method = RequestMethod.GET)
    public String displayNewNewsPage(Model model, HttpSession session) {
        model.addAttribute("formNews", new FormNews());
        return "news_section_new";
    }

    @RequestMapping(value = "/news/new", method = RequestMethod.POST)
    public ModelAndView createNews(@ModelAttribute @Valid FormNews news,
                                   BindingResult result,
                                   HttpSession session) {

        User user = (User)session.getAttribute("user");

        if (result.hasErrors())
            return new ModelAndView("news_section_new", "formNews", news);

        News db_news = new News();

        db_news.setCategoryId(1);
        db_news.setMainImageId(1);
        db_news.setTitle(news.getTitle());
        db_news.setContent(news.getContent());
        db_news.setDescription(news.getDescription());
        db_news.setPublicationTime(TimestampUtils.isValid(news.getPublicationTime()).y);

        List<Integer> authorsID = new ArrayList<>();
        authorsID.add(user.getId());
        db_news.setAuthors(authorsID);

        try {
            microserviceNewsProxy.saveNews(db_news);
        } catch (FeignException e) {
            logger.error("Failed to insert news", e);
            return new ModelAndView("error");
        }

        return new ModelAndView("redirect:/news");
    }

    @RequestMapping(value = "/news/delete/{id}", method = RequestMethod.GET)
    public String deleteNews (@PathVariable (name = "id") Integer id, HttpSession session) {

        try {
            microserviceNewsProxy.deleteNews(id);
        } catch (FeignException e) {
            logger.error("failed to delete news", e);
            return "error";
        }

        return "redirect:/news";
    }
}
