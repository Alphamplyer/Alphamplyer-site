package com.alphamplyer.microservice.news.repositories.rowMappers;

import com.alphamplyer.microservice.news.models.News;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsRowMapper implements RowMapper<News> {

    @Override
    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        News news = new News();

        news.setId(rs.getInt("id"));
        news.setCategoryId(rs.getInt("category_id"));
        news.setMainImageId(rs.getInt("main_image_id"));

        news.setPublicationTime(rs.getTimestamp("publication_time"));

        news.setTitle(rs.getString("title"));
        news.setDescription(rs.getString("description"));
        news.setContent(rs.getString("content"));

        news.setCreatedAt(rs.getTimestamp("created_at"));
        news.setUpdatedAt(rs.getTimestamp("updated_at"));

        return news;
    }
}
