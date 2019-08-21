package com.alphamplyer.microservice.news.repositories.dao.interf;

import com.alphamplyer.microservice.news.models.NewsCategory;

import java.util.List;

public interface INewsCategoryRepository {

    NewsCategory getById(Integer id);

    NewsCategory getParent(Integer id);

    List<NewsCategory> getChild(Integer id);

    List<NewsCategory> getMainCategories();

    NewsCategory save(NewsCategory category);

    void update(Integer id, NewsCategory category);

    void delete(Integer id);
}
