package com.alphamplyer.microservice.users.repositories.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class DAORepository  {

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
     * Return a string with all element of the list join like that : (e1, e2, ..., e(n-1), e(n))
     * @param list list of element
     * @param <T> type of elements of the list
     * @return a string with all element of the list join like that : (e1, e2, ..., e(n-1), e(n))
     */
    protected <T> String listToSQLList(List<T> list) {
        StringBuilder sqlList = new StringBuilder("(");

        for (int i = 0; i < list.size(); i++) {
            sqlList.append(list.get(i).toString());

            if (i < list.size() - 1) {
                sqlList.append(", ");
            }
        }

        sqlList.append(")");

        return sqlList.toString();
    }
}
