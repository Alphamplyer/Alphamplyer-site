package com.alphamplyer.microservice.news.repositories.dao.interf;

import com.alphamplyer.microservice.news.models.News;

import java.util.List;

public interface INewsRepository {

    News getById(Integer id);

    List<News> getNews(Integer offset, Integer limit);

    List<News> getNewsByCategoryId(Integer categoryId, Integer offset, Integer limit);

    List<News> getNewsByAuthorId(Integer authorId, Integer offset, Integer limit);

    News save(News news);

    void update(Integer id, News news);

    void delete(Integer id);

    void deleteNewsOfCategory(Integer category_id);
}
