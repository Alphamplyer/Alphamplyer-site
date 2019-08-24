package com.alphamplyer.microservice.users.repositories.rowMappers;

import com.alphamplyer.microservice.users.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setRoleId(rs.getInt("role_id"));

        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));

        user.setPassword(rs.getString("password"));
        user.setSalt(rs.getString("salt"));

        user.setTermAccepted(rs.getBoolean("term_accepted"));
        user.setEmailValidated(rs.getBoolean("email_validated"));

        user.setBirthDate(rs.getDate("birth_date"));

        user.setCreatedAt(rs.getTimestamp("created_at"));
        user.setUpdatedAt(rs.getTimestamp("updated_at"));

        return user;
    }
}
