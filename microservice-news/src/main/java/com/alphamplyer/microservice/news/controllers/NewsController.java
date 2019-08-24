package com.alphamplyer.microservice.news.controllers;

import com.alphamplyer.microservice.news.exceptions.NotFoundException;
import com.alphamplyer.microservice.news.exceptions.UnableToDeleteException;
import com.alphamplyer.microservice.news.exceptions.UnableToInsertException;
import com.alphamplyer.microservice.news.exceptions.UnableToUpdateException;
import com.alphamplyer.microservice.news.models.News;
import com.alphamplyer.microservice.news.models.NewsCategory;
import com.alphamplyer.microservice.news.repositories.dao.interf.INewsCategoryRepository;
import com.alphamplyer.microservice.news.repositories.dao.interf.INewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsController {

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    private INewsRepository newsRepository;
    private INewsCategoryRepository newsCategoryRepository;

    @Autowired
    public void setNewsRepository(INewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Autowired
    public void setNewsCategoryRepository(INewsCategoryRepository newsCategoryRepository) {
        this.newsCategoryRepository = newsCategoryRepository;
    }


    //region // --- < GET MAPPING > -------------------------------------------------------------------------------//

    @GetMapping(value = "/news/{id}")
    public ResponseEntity<News> getNewsByID(@PathVariable(name = "id") Integer id,
                                            @RequestParam(name = "includePublished", required = false) Boolean includePublished) {
        News news;

        try {
            news = newsRepository.getById(id, includePublished);
            List<Integer> IDs = newsRepository.getNewsAuthor(news.getId());
            news.setAuthors(IDs);
        } catch (DataAccessException e) {
            logger.error("News not found", e);
            news = null;
        }

        if (news == null) {
            throw new NotFoundException(String.format("News with ID = %d was not found", id));
        }

        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping(value = "/news")
    public ResponseEntity<List<News>> getNews(@RequestParam(name = "offset", required = false) Integer offset,
                                              @RequestParam(name = "limit", required = false) Integer limit,
                                              @RequestParam(name = "includePublished", required = false) Boolean includePublished) {
        List<News> news;

        try {
            news = newsRepository.getNews(offset, limit, includePublished);
        } catch (DataAccessException e) {
            logger.error("News not found", e);
            news = null;
        }

        if (news == null) {
            throw new NotFoundException("News was not found in specified range");
        }

        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping(value = "/categories/{id}/news")
    public ResponseEntity<List<News>> getNewsOfCategory(@PathVariable(name = "id") Integer id,
                                                        @RequestParam(name = "offset", required = false) Integer offset,
                                                        @RequestParam(name = "limit", required = false) Integer limit,
                                                        @RequestParam(name = "includePublished", required = false) Boolean includePublished) {
        List<News> news;

        try {
            news = newsRepository.getNewsByCategoryId(id, offset, limit, includePublished);
        } catch (DataAccessException e) {
            logger.error("News not found in category", e);
            news = null;
        }

        if (news == null) {
            throw new NotFoundException(String.format("News with category ID = %d was not found", id));
        }

        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping(value = "/news/author/{id}")
    public ResponseEntity<List<News>> getNewsOfAuthor(@PathVariable(name = "id") Integer id,
                                                      @RequestParam(name = "offset", required = false) Integer offset,
                                                      @RequestParam(name = "limit", required = false) Integer limit,
                                                      @RequestParam(name = "includePublished", required = false) Boolean includePublished) {
        List<News> news;

        try {
            news = newsRepository.getNewsByAuthorId(id, offset, limit, includePublished);
        } catch (DataAccessException e) {
            logger.error("News not found in author's written news", e);
            news = null;
        }

        if (news == null) {
            throw new NotFoundException(String.format("News with author ID = %d was not found", id));
        }

        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping(value = "/categories/{id}")
    public ResponseEntity<NewsCategory> getCategoryById(@PathVariable(name = "id") Integer id) {
        NewsCategory category;

        try {
            category = newsCategoryRepository.getById(id);
        } catch (DataAccessException e) {
            logger.error("News category not found", e);
            category = null;
        }

        if (category == null) {
            throw new NotFoundException(String.format("News category with ID = %d was not found", id));
        }

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping(value = "/categories/{id}/parent")
    public ResponseEntity<NewsCategory> getParentCategoryById(@PathVariable(name = "id") Integer id) {
        NewsCategory category;

        try {
            category = newsCategoryRepository.getParent(id);
        } catch (DataAccessException e) {
            logger.error("Parent News category not found", e);
            category = null;
        }

        if (category == null) {
            throw new NotFoundException(String.format("News category with ID = %d has no parent", id));
        }

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping(value = "/categories/{id}/child")
    public ResponseEntity<List<NewsCategory>> getNewsCategoryChild(@PathVariable(name = "id") Integer id) {
        List<NewsCategory> categories;

        try {
            categories = newsCategoryRepository.getChild(id);
        } catch (DataAccessException e) {
            logger.error("News category child was not found", e);
            categories = null;
        }

        if (categories == null) {
            throw new NotFoundException(String.format("News category child was not found with news category ID = %d", id));
        }

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping(value = "/categories/mains")
    public ResponseEntity<List<NewsCategory>> getMainCategories() {
        List<NewsCategory> categories;

        try {
            categories = newsCategoryRepository.getMainCategories();
        } catch (DataAccessException e) {
            logger.error("Main news categories was not found", e);
            categories = null;
        }

        if (categories == null) {
            throw new NotFoundException("Main news categories was not found");
        }

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    //endregion

    //region // --- < POST MAPPING > ------------------------------------------------------------------------------//

    @PostMapping("/news/save")
    public ResponseEntity<News> saveNews(@RequestBody News news) {
        News rNews;

        try {
            rNews = newsRepository.save(news);
        } catch (DataAccessException e) {
            logger.error("Failed to save", e);
            rNews = null;
        }

        if (rNews == null) {
            throw new UnableToInsertException("Failed to save news");
        }

        return new ResponseEntity<>(rNews, HttpStatus.CREATED);
    }

    @PostMapping("/categories/save")
    public ResponseEntity<NewsCategory> saveNewsCategory(@RequestBody NewsCategory newsCategory) {
        NewsCategory rNewsCategory;

        try {
            rNewsCategory = newsCategoryRepository.save(newsCategory);
        } catch (DataAccessException e) {
            logger.error("Failed to save", e);
            rNewsCategory = null;
        }

        if (rNewsCategory == null) {
            throw new UnableToInsertException("Failed to save news category");
        }

        return new ResponseEntity<>(rNewsCategory, HttpStatus.CREATED);
    }

    //endregion

    //region // --- < PUT MAPPING > -------------------------------------------------------------------------------//

    @PutMapping("/news/{id}/update")
    public ResponseEntity<Void> updateNews(@PathVariable(name = "id") Integer id, @RequestBody News news) {

        try {
            newsRepository.update(id, news);
        } catch (DataAccessException e) {
            logger.error("Failed to update", e);
            throw new UnableToUpdateException("Failed to update news with ID = " + id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/categories/{id}/update")
    public ResponseEntity<Void> updateNewsCategory(@PathVariable(name = "id") Integer id, @RequestBody NewsCategory newsCategory) {

        try {
            newsCategoryRepository.update(id, newsCategory);
        } catch (DataAccessException e) {
            logger.error("Failed to update", e);
            throw new UnableToUpdateException("Failed to update news category with ID = " + id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //endregion

    //region // --- < DELETE MAPPING > ----------------------------------------------------------------------------//

    @DeleteMapping("/news/{id}/delete")
    public ResponseEntity<Void> updateNews(@PathVariable(name = "id") Integer id) {

        try {
            newsRepository.delete(id);
        } catch (DataAccessException e) {
            logger.error("Failed to delete", e);
            throw new UnableToDeleteException("Failed to delete news with ID = " + id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}/delete")
    public ResponseEntity<Void> updateNewsCategory(@PathVariable(name = "id") Integer id) {

        try {
            newsCategoryRepository.delete(id);
        } catch (DataAccessException e) {
            logger.error("Failed to delete", e);
            throw new UnableToDeleteException("Failed to delete news category with ID = " + id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //endregion
}
