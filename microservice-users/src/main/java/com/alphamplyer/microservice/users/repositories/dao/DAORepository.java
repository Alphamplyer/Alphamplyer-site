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

    protected String buildSQLOffsetLimit(Integer offset, Integer limit) {
        String sql = "";

        if (offset != null && offset > 0) sql += " OFFSET " + offset.toString();
        if (limit != null && limit > 0) sql += " LIMIT " + limit.toString();

        return sql.trim();
    }

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
