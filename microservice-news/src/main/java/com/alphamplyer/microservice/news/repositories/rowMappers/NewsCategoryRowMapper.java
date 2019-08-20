package com.alphamplyer.microservice.news.repositories.rowMappers;

import com.alphamplyer.microservice.news.models.NewsCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsCategoryRowMapper implements RowMapper<NewsCategory> {

    @Override
    public NewsCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        NewsCategory category = new NewsCategory();

        category.setId(rs.getInt("id"));
        category.setParentId(rs.getInt("parent_id"));
        category.setCreatorId(rs.getInt("creator_id"));
        category.setName(rs.getString("name"));
        category.setDescription(rs.getString("description"));
        category.setCreatedAt(rs.getTimestamp("created_at"));
        category.setUpdatedAt(rs.getTimestamp("updated_at"));

        return category;
    }
}
