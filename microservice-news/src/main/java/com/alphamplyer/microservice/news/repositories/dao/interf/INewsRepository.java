package com.alphamplyer.microservice.news.repositories.dao.interf;

import com.alphamplyer.microservice.news.models.News;

import java.util.List;

public interface INewsRepository {

    /**
     * Return news by its ID
     * @param id news ID
     * @param includeNotPublished does we search in all news (true), or in published (false)
     * @return a news
     */
    News getById(Integer id, Boolean includeNotPublished);

    /**
     * Return a list of news
     * @param offset defined from when to get news, set it to 0 or null avoid this parameters
     * @param limit defines the number of news wanted from offset, set it to 0 or null avoid this parameters
     * @param includeNotPublished does we search in all news (true), or in published (false)
     * @return a list of news
     */
    List<News> getNews(Integer offset, Integer limit, Boolean includeNotPublished);

    /**
     * Return a list of news by category ID
     * @param offset defined from when to get news, set it to 0 or null avoid this parameters
     * @param limit defines the number of news wanted from offset, set it to 0 or null avoid this parameters
     * @param includeNotPublished does we search in all news (true), or in published (false)
     * @return a list of news
     */
    List<News> getNewsByCategoryId(Integer categoryId, Integer offset, Integer limit, Boolean includeNotPublished);

    /**
     * Return a list of news by author ID
     * @param offset defined from when to get news, set it to 0 or null avoid this parameters
     * @param limit defines the number of news wanted from offset, set it to 0 or null avoid this parameters
     * @param includeNotPublished does we search in all news (true), or in published (false)
     * @return a list of news
     */
    List<News> getNewsByAuthorId(Integer authorId, Integer offset, Integer limit, Boolean includeNotPublished);

    /**
     * Get list of author from news ID
     * @param news_id news ID
     * @return list of author
     */
    List<Integer> getNewsAuthor(Long news_id);

    /**
     * Save a news
     * @param news news data
     * @return saved news from database
     */
    News save(News news);

    /**
     * Update news from its ID
     * @param id news ID
     * @param news news data
     */
    void update(Integer id, News news);

    /**
     * Delete news from its ID
     * @param id news ID
     */
    void delete(Integer id);

    /**
     * Delete all news of one category
      * @param category_id news category ID
     */
    void deleteNewsOfCategory(Integer category_id);
}
