package com.alphamplyer.microservice.userspermissions.repositories.rowMappers;

import com.alphamplyer.microservice.userspermissions.models.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();

        role.setId(rs.getInt("id"));
        role.setIndex(rs.getInt("index"));
        role.setName(rs.getString("name"));

        return role;
    }
}
