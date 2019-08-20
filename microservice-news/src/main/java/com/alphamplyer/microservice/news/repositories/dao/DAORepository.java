package com.alphamplyer.microservice.news.repositories.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DAORepository {

    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    protected void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    protected String buildSQLOffsetLimit(Integer offset, Integer limit) {
        String sql = "";

        if (offset != null && offset > 0) sql += " OFFSET " + offset.toString();
        if (limit != null && limit > 0) sql += " LIMIT " + limit.toString();

        return sql.trim();
    }

    protected String buildSQLCondition(Boolean active, String sqlToAdd) {
        if (active != null && active)
            return sqlToAdd;
        else
            return "";
    }
}
