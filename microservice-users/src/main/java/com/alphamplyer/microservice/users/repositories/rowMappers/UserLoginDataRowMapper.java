package com.alphamplyer.microservice.users.repositories.rowMappers;

import com.alphamplyer.microservice.users.models.UserLoginData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginDataRowMapper implements RowMapper<UserLoginData> {

    @Override
    public UserLoginData mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserLoginData userLoginData = new UserLoginData();

        userLoginData.setUsername(rs.getString("username"));
        userLoginData.setEmail(rs.getString("email"));
        userLoginData.setPassword(rs.getString("password"));

        return userLoginData;
    }
}
