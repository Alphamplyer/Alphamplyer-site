package com.alphamplyer.microservice.orders.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Basic methods and variables to be use into repositories
 */
@Component
public class DAORepository {

    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    protected void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Returns the piece of an SQL query to limit or not the result according to the given parameters.
     * @param offset from how many results does the selection start?
     * @param limit how many results are expected at the maximum?
     * @return A string representing a piece of SQL query
     */
    protected String buildSQLOffsetLimit(Integer offset, Integer limit) {
        String sql = "";

        if (offset != null && offset > 0) sql += " OFFSET " + offset.toString();
        if (limit != null && limit > 0) sql += " LIMIT " + limit.toString();

        return sql.trim();
    }

    /**
     * Return a piece of SQL query if the state of the condition is true
     * @param active the state of the condition
     * @param sqlToAdd the piece of SQL query to add
     * @return A string representing a piece of SQL query
     */
    protected String buildSQLCondition(Boolean active, String sqlToAdd) {
        if (active != null && active)
            return sqlToAdd;
        else
            return "";
    }
}
