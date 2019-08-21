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

    @GetMapping(value = "/news/{id}")
    News getNewsByID(@PathVariable(name = "id") Integer id, @RequestParam(name = "includePublished", required = false) Boolean includePublished);

    @GetMapping(value = "/news")
    List<News> getNews(@RequestParam(name = "offset", required = false) Integer offset,
                       @RequestParam(name = "limit", required = false) Integer limit,
                       @RequestParam(name = "includePublished", required = false) Boolean includePublished);

    @GetMapping(value = "/categories/{id}/news")
    List<News> getNewsOfCategory(@PathVariable(name = "id") Integer id,
                                 @RequestParam(name = "offset", required = false) Integer offset,
                                 @RequestParam(name = "limit", required = false) Integer limit,
                                 @RequestParam(name = "includePublished", required = false) Boolean includePublished);

    @GetMapping(value = "/news/author/{id}")
    List<News> getNewsOfAuthor(@PathVariable(name = "id") Integer id,
                               @RequestParam(name = "offset", required = false) Integer offset,
                               @RequestParam(name = "limit", required = false) Integer limit,
                               @RequestParam(name = "includePublished", required = false) Boolean includePublished);

    @GetMapping(value = "/categories/{id}")
    NewsCategory getCategoryById(@PathVariable(name = "id") Integer id);

    @GetMapping(value = "/categories/{id}/parent")
    NewsCategory getParentCategoryById(@PathVariable(name = "id") Integer id);

    @GetMapping(value = "/categories/{id}/child")
    List<NewsCategory> getNewsCategoryChild(@PathVariable(name = "id") Integer id);

    @GetMapping(value = "/categories/mains")
    List<NewsCategory> getMainCategories();

    //endregion

    //region // --- < POST MAPPING > ------------------------------------------------------------------------------//

    @PostMapping("/news/save")
    News saveNews(@RequestBody News news);

    @PostMapping("/categories/save")
    NewsCategory saveNewsCategory(@RequestBody NewsCategory newsCategory);

    //endregion

    //region // --- < PUT MAPPING > -------------------------------------------------------------------------------//

    @PutMapping("/news/{id}/update")
    Void updateNews(@PathVariable(name = "id") Integer id, @RequestBody News news);

    @PutMapping("/categories/{id}/update")
    Void updateNewsCategory(@PathVariable(name = "id") Integer id, @RequestBody NewsCategory newsCategory);

    //endregion

    //region // --- < DELETE MAPPING > ----------------------------------------------------------------------------//

    @DeleteMapping("/news/{id}/delete")
    Void updateNews(@PathVariable(name = "id") Integer id);

    @DeleteMapping("/categories/{id}/delete")
    Void updateNewsCategory(@PathVariable(name = "id") Integer id);

    //endregion
}
