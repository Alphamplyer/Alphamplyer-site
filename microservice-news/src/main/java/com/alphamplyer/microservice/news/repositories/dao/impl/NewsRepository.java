package com.alphamplyer.microservice.news.repositories.dao.impl;

import com.alphamplyer.microservice.news.models.News;
import com.alphamplyer.microservice.news.repositories.dao.DAORepository;
import com.alphamplyer.microservice.news.repositories.dao.interf.INewsRepository;
import com.alphamplyer.microservice.news.repositories.rowMappers.NewsAuthorsRowMapper;
import com.alphamplyer.microservice.news.repositories.rowMappers.NewsRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class NewsRepository extends DAORepository implements INewsRepository {

    @Override
    public News getById(Integer id, Boolean includeNotPublished) {
        String sql = "SELECT * FROM news WHERE id = :id"
            + buildSQLCondition(includeNotPublished, " AND publication_time < NOW()");

        RowMapper<News> rowMapper = new NewsRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        News news;

        try {
            news = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return news;
    }

    @Override
    public List<News> getNews(Integer offset, Integer limit, Boolean includeNotPublished) {
        String sql = "SELECT * FROM news "
            + buildSQLCondition(includeNotPublished, "WHERE publication_time < NOW()")
            + " ORDER BY publication_time DESC "
            + buildSQLOffsetLimit(offset, limit);

        RowMapper<News> rowMapper = new NewsRowMapper();

        List<News> newsList = jdbcTemplate.query(sql, rowMapper);

        if (newsList.isEmpty())
            return null;

        return newsList;
    }

    @Override
    public List<Integer> getNewsAuthor(Long news_id) {
        String sql = "SELECT author_id FROM news_authors WHERE news_id = :news_id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("news_id", news_id);

        RowMapper<Integer> rowMapper = new NewsAuthorsRowMapper();
        List<Integer> authorIDs = namedParameterJdbcTemplate.query(sql, params, rowMapper);

        if (authorIDs.isEmpty())
            return null;

        return authorIDs;
    }

    @Override
    public List<News> getNewsByCategoryId(Integer categoryId, Integer offset, Integer limit, Boolean includeNotPublished) {
        String sql = "WITH RECURSIVE category_collection AS ( " +
            "SELECT id, parent_id, creator_id, name, description, created_at, updated_at " +
            "FROM news_categories " +
            "WHERE id = :category_id " +
            "UNION ALL " +
            "SELECT nc.id, nc.parent_id, nc.creator_id, nc.name, nc.description, nc.created_at, nc.updated_at FROM news_categories AS nc " +
            "JOIN category_collection ON category_collection.id = nc.parent_id " +
            ") " +
            "SELECT n.* FROM news n, category_collection c WHERE n.category_id = c.id "
            + buildSQLCondition(includeNotPublished, " AND publication_time < NOW()")
            + " ORDER BY publication_time DESC "
            + buildSQLOffsetLimit(offset, limit);

        RowMapper<News> rowMapper = new NewsRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("category_id", categoryId);

        List<News> newsList = namedParameterJdbcTemplate.query(sql, params, rowMapper);

        if (newsList.isEmpty())
            return null;

        return newsList;
    }

    @Override
    public List<News> getNewsByAuthorId(Integer authorId, Integer offset, Integer limit, Boolean includeNotPublished) {
        String sql = "SELECT n.* FROM news n, news_authors na WHERE na.news_id = n.id AND na.author_id = :authorId "
            + buildSQLCondition(includeNotPublished, " AND publication_time < NOW()")
            + " ORDER BY publication_time DESC "
            + buildSQLOffsetLimit(offset, limit);

        RowMapper<News> rowMapper = new NewsRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author_id", authorId);

        List<News> newsList = namedParameterJdbcTemplate.query(sql, params, rowMapper);

        if (newsList.isEmpty())
            return null;

        return newsList;
    }

    @Override
    public News save(News news) {

        String sql_news = "INSERT INTO news (category_id, main_image_id, title, content, description, " +
            "publication_time, created_at, updated_at) " +
            "VALUES (:category_id, :main_image_id, :title, :content, :description, " +
            ":publication_time, :created_at, :updated_at)";

        Timestamp now = new Timestamp(System.currentTimeMillis());

        MapSqlParameterSource params_news = new MapSqlParameterSource();
        params_news.addValue("category_id", news.getCategoryId());
        params_news.addValue("main_image_id", news.getMainImageId());
        params_news.addValue("title", news.getTitle());
        params_news.addValue("content", news.getContent());
        params_news.addValue("description", news.getDescription());
        params_news.addValue("publication_time", news.getPublicationTime() == null ? now : news.getPublicationTime());
        params_news.addValue("created_at", now);
        params_news.addValue("updated_at", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql_news, params_news, keyHolder);

        if (keyHolder.getKey() == null) {
            return null;
        }

        news.setId(keyHolder.getKey().longValue());
        news.setCreatedAt(now);
        news.setUpdatedAt(now);

        String sql_authors = "INSERT INTO news_authors (news_id, author_id) VALUES ";

        for (int i = 0; i < news.getAuthors().size(); i++) {
            if (i > 0)
                sql_authors += ", ";
            sql_authors += "(" + news.getId() + ", " + news.getAuthors().get(i) + ")";
        }

        jdbcTemplate.update(sql_authors);

        return news;
    }

    @Override
    public void update(Integer id, News news) {
        String sql = "UPDATE news SET category_id = :category_id, main_image_id = :main_image_id , title = :title, " +
            "content = :content, description = :description, updated_at = :updated_at WHERE id = :id";

        Timestamp now = new Timestamp(System.currentTimeMillis());

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("category_id", news.getCategoryId());
        params.addValue("main_image_id", news.getMainImageId());
        params.addValue("title", news.getTitle());
        params.addValue("content", news.getContent());
        params.addValue("description", news.getDescription());
        params.addValue("updated_at", now);

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(Integer id) {
        String delete_news = "DELETE FROM news WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        namedParameterJdbcTemplate.update(delete_news, params);
    }

    @Override
    public void deleteNewsOfCategory(Integer category_id) {
        String sql = "DELETE FROM news WHERE category_id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", category_id);

        namedParameterJdbcTemplate.update(sql, params);
    }
}
