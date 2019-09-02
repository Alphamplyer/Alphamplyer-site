package com.alphamplyer.microservice.news.repositories.dao.interf;

import com.alphamplyer.microservice.news.models.NewsCategory;

import java.util.List;

public interface INewsCategoryRepository {

    /**
     * Get news category by its ID
     * @param id ID of the news category
     * @return a news category or null
     */
    NewsCategory getById(Integer id);

    /**
     * Get news category parent of the category with the given ID
     * @param id category ID
     * @return a news category or null
     */
    NewsCategory getParent(Integer id);

    /**
     * Get all child of a news category with the given ID
     * @param id category ID
     * @return a list of news category or null
     */
    List<NewsCategory> getChild(Integer id);

    /**
     * Get the main categories. (categories without parent id)
     * @return a list of news category or null
     */
    List<NewsCategory> getMainCategories();

    /**
     * Save a news category
     * @param category news category data
     * @return the saved news category from database
     */
    NewsCategory save(NewsCategory category);

    /**
     * update a news category
     * @param id news category ID to update
     * @param category news category data
     */
    void update(Integer id, NewsCategory category);

    /**
     * delete a news category
     * @param id news category ID to delete
     */
    void delete(Integer id);
}
