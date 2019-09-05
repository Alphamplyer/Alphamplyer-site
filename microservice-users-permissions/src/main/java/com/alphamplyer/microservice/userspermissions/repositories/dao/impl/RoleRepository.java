package com.alphamplyer.microservice.userspermissions.repositories.dao.impl;

import com.alphamplyer.microservice.userspermissions.models.Role;
import com.alphamplyer.microservice.userspermissions.repositories.dao.DAORepository;
import com.alphamplyer.microservice.userspermissions.repositories.dao.interf.IRoleRepository;
import com.alphamplyer.microservice.userspermissions.repositories.rowMappers.RoleRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Repository
public class RoleRepository extends DAORepository implements IRoleRepository {

    @Override
    public Role getById(Integer id) {
        String sql = "SELECT * FROM roles WHERE id = :id";

        RowMapper<Role> rowMapper = new RoleRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        Role role;

        try {
            role = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return role;
    }

    @Override
    public Role getByName(String name) {
        String sql = "SELECT * FROM roles WHERE name = :name";

        RowMapper<Role> rowMapper = new RoleRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);

        Role role;

        try {
            role = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return role;
    }

    @Override
    public List<Role> getRoles() {
        String sql = "SELECT * FROM roles ORDER BY id";

        RowMapper<Role> rowMapper = new RoleRowMapper();

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Role add(Role role) {
        String sql = "INSERT INTO roles(name) VALUES (:name)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", role.getName());

        namedParameterJdbcTemplate.update(sql, params, keyHolder);

        HashMap<String, Object> keys;

        if (keyHolder.getKeys() != null)
            keys = new HashMap<>(keyHolder.getKeys());
        else
            return null;

        role.setId((Integer)keys.get("id"));
        role.setIndex((Integer)keys.get("index"));

        return role;
    }

    @Override
    public void save(Role role) {
        String sql =    "UPDATE roles SET index = index + 1 WHERE " +
                            "index IN (SELECT index FROM roles WHERE index >= :index ORDER BY index DESC) " +
                            "AND EXISTS (SELECT index FROM roles WHERE id = :id AND index <> :index); " +
                        "UPDATE roles SET index = :index, name = :name WHERE id = :id;";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", role.getId());
        params.addValue("index", role.getIndex());
        params.addValue("name", role.getName());

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void remove(Integer id) {

    }
}
