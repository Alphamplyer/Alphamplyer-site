package com.alphamplyer.website.main.proxies;

import com.alphamplyer.website.main.beans.News;
import com.alphamplyer.website.main.beans.NewsCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-news", url = "localhost:10201")
public interface MicroserviceNewsProxy {

    //region // --- < GET MAPPING > -------------------------------------------------------------------------------//

    /**
     * Return news by its ID
     * @param id news ID
     * @param includePublished does we search in all news (false), or in published (true)
     * @return if founded, a news, else, a 404 error
     */
    @GetMapping(value = "/news/{id}")
    News getNewsByID(@PathVariable(name = "id") Integer id, @RequestParam(name = "includePublished", required = false) Boolean includePublished);

    /**
     * Returns all news between offset and (offset + limit)
     * @param offset defined from when to get news, set it to 0 or null avoid this parameters
     * @param limit defines the number of news wanted from offset, set it to 0 or null avoid this parameters
     * @param includePublished does we search in all news (false), or in published (true)
     * @return a list of news or 404 error if not founded
     */
    @GetMapping(value = "/news")
    List<News> getNews(@RequestParam(name = "offset", required = false) Integer offset,
                       @RequestParam(name = "limit", required = false) Integer limit,
                       @RequestParam(name = "includePublished", required = false) Boolean includePublished);

    /**
     * Return all news between offset and (offset + limit) in a list of news of one category
     * @param id category ID
     * @param offset defined from when to get news, set it to 0 or null avoid this parameters
     * @param limit defines the number of news wanted from offset, set it to 0 or null avoid this parameters
     * @param includePublished does we search in all news (false), or in published (true)
     * @return a list of news or 404 error if not founded
     */
    @GetMapping(value = "/categories/{id}/news")
    List<News> getNewsOfCategory(@PathVariable(name = "id") Integer id,
                                 @RequestParam(name = "offset", required = false) Integer offset,
                                 @RequestParam(name = "limit", required = false) Integer limit,
                                 @RequestParam(name = "includePublished", required = false) Boolean includePublished);

    /**
     * Return all news between offset and (offset + limit) in a list of news of one author
     * @param id author ID
     * @param offset defined from when to get news, set it to 0 or null avoid this parameters
     * @param limit defines the number of news wanted from offset, set it to 0 or null avoid this parameters
     * @param includePublished does we search in all news (false), or in published (true)
     * @return a list of news or 404 error if not founded
     */
    @GetMapping(value = "/news/author/{id}")
    List<News> getNewsOfAuthor(@PathVariable(name = "id") Integer id,
                               @RequestParam(name = "offset", required = false) Integer offset,
                               @RequestParam(name = "limit", required = false) Integer limit,
                               @RequestParam(name = "includePublished", required = false) Boolean includePublished);

    /**
     * Return a category from its ID
     * @param id category ID
     * @return a category or a 404 error if not founded
     */
    @GetMapping(value = "/categories/{id}")
    NewsCategory getCategoryById(@PathVariable(name = "id") Integer id);

    /**
     * Return the parent category from its child ID
     * @param id child category ID
     * @return a category or a 404 error if not founded
     */
    @GetMapping(value = "/categories/{id}/parent")
    NewsCategory getParentCategoryById(@PathVariable(name = "id") Integer id);

    /**
     * Get all child categories of one category
     * @param id category ID
     * @return a list of categories or a 404 error if not founded
     */
    @GetMapping(value = "/categories/{id}/child")
    List<NewsCategory> getNewsCategoryChild(@PathVariable(name = "id") Integer id);

    /**
     * Get main categories (categories that do not have a parent)
     * @return a list of categories or a 404 error if not founded
     */
    @GetMapping(value = "/categories/mains")
    List<NewsCategory> getMainCategories();

    //endregion

    //region // --- < POST MAPPING > ------------------------------------------------------------------------------//

    /**
     * Ask to save a news in database
     * @param news news to save
     * @return the saved news or an internal error (5OO)
     */
    @PostMapping("/news/save")
    News saveNews(@RequestBody News news);

    /**
     * Ask to save a news category in database
     * @param newsCategory news category to save
     * @return the saved news category or an internal error (5OO)
     */
    @PostMapping("/categories/save")
    NewsCategory saveNewsCategory(@RequestBody NewsCategory newsCategory);

    //endregion

    //region // --- < PUT MAPPING > -------------------------------------------------------------------------------//

    /**
     * Ask to update a news
     * @param id news id
     * @param news new news data
     * @return OK http status or Internal error if failed
     */
    @PutMapping("/news/{id}/update")
    Void updateNews(@PathVariable(name = "id") Integer id, @RequestBody News news);

    /**
     * Ask to update a news category
     * @param id news category id
     * @param newsCategory new news category data
     * @return OK http status or Internal error if failed
     */
    @PutMapping("/categories/{id}/update")
    Void updateNewsCategory(@PathVariable(name = "id") Integer id, @RequestBody NewsCategory newsCategory);

    //endregion

    //region // --- < DELETE MAPPING > ----------------------------------------------------------------------------//

    /**
     * Ask to delete a news
     * @param id news ID to delete
     * @return OK http status or Internal error if failed
     */
    @DeleteMapping("/news/{id}/delete")
    Void updateNews(@PathVariable(name = "id") Integer id);

    /**
     * Ask to delete a news category
     * @param id news category ID to delete
     * @return OK http status or Internal error if failed
     */
    @DeleteMapping("/categories/{id}/delete")
    Void updateNewsCategory(@PathVariable(name = "id") Integer id);

    //endregion
}
