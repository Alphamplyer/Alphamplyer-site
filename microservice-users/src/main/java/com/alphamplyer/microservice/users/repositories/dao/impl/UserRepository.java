package com.alphamplyer.microservice.users.repositories.dao.impl;

import com.alphamplyer.microservice.users.models.User;
import com.alphamplyer.microservice.users.repositories.dao.DAORepository;
import com.alphamplyer.microservice.users.repositories.dao.interf.IUserRepository;
import com.alphamplyer.microservice.users.repositories.rowMappers.UserRowMapper;
import com.alphamplyer.microservice.users.utlis.Password;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository extends DAORepository implements IUserRepository {

    @Override
    public User getById(Integer id) {
        String sql = "SELECT * FROM users WHERE id = :id";

        RowMapper<User> rowMapper = new UserRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        User user;

        try {
            user = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return user;
    }

    @Override
    public User getByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = :username";

        RowMapper<User> rowMapper = new UserRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);

        User user;

        try {
            user = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return user;
    }

    @Override
    public User getByEmail(String email) {
        System.out.println(email);

        String sql = "SELECT * FROM users WHERE email = :email";

        RowMapper<User> rowMapper = new UserRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);

        return namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
    }

    @Override
    public Integer countUserWithSameUsername(String username) {
        String sql = "SELECT Count(*) FROM users WHERE username = :username";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);

        Integer number_of_user;

        try {
            number_of_user = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("===============*===============\n" + e);
            return null;
        }

        return number_of_user;
    }

    @Override
    public Integer countUserWithSameEmail(String email) {
        String sql = "SELECT Count(*) FROM users WHERE email = :email";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);

        Integer number_of_user;

        try {
            number_of_user = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return number_of_user;
    }

    @Override
    public List<User> getAll(Integer offset, Integer limit) {
        String sql = "SELECT * FROM users ORDER BY username " + buildSQLOffsetLimit(offset, limit);

        RowMapper<User> rowMapper = new UserRowMapper();

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<User> getAllByRoleID(Integer roleId, Integer offset, Integer limit) {
        String sql = "SELECT * FROM users WHERE role_id = :roleId " + buildSQLOffsetLimit(offset, limit);

        RowMapper<User> rowMapper = new UserRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleId", roleId);

        return namedParameterJdbcTemplate.query(sql, params, rowMapper);
    }

    @Override
    public List<User> getAllByListIDs(List<Integer> listIDs) {
        String sql = "SELECT * FROM users WHERE id IN " + listToSQLList(listIDs);

        RowMapper<User> rowMapper = new UserRowMapper();

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public User insert(User user) {
        String sql = "INSERT INTO users (username, email, password, salt, term_accepted, birth_date, updated_at, created_at) VALUES (:username, :email, :password, :salt, :term_accepted, :birth_date, :updated_at, :created_at)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", user.getUsername());
        params.addValue("email", user.getEmail());
        params.addValue("password", user.getPassword());
        params.addValue("salt", user.getSalt());
        params.addValue("term_accepted", user.getTermAccepted());
        params.addValue("birth_date", user.getBirthDate());
        params.addValue("updated_at", now);
        params.addValue("created_at", now);

        namedParameterJdbcTemplate.update(sql, params, keyHolder);

        HashMap<String, Object> keys;

        if (keyHolder.getKeys() != null)
            keys = new HashMap<String, Object>(keyHolder.getKeys());
        else
            return null;

        user.setId((Integer)keys.get("id"));
        user.setRoleId((Integer)keys.get("role_id"));
        user.setEmailValidated(false);
        user.setUpdatedAt(now);
        user.setCreatedAt(now);

        return user;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", user.getId());

        boolean anyThingToUpdate = false;

        if (user.getRoleId() != null) {
            sql += " role_id = :roleId,";
            params.addValue("roleId", user.getRoleId());
            anyThingToUpdate = true;
        }
        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            sql += " username = :username,";
            params.addValue("username", user.getUsername());
            anyThingToUpdate = true;
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            sql += "  email = :email,";
            params.addValue("email", user.getEmail());
            anyThingToUpdate = true;

            if (user.getEmailValidated() == null) {
                sql += " email_validated = :emailValidated,";
                params.addValue("emailValidated", false);
            }
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            sql += " password = :password,";
            String newSalt = Password.generateSalt(128);
            params.addValue("password", Password.generateSecurePassword(user.getPassword(), newSalt));

            sql += " salt = :salt,";
            params.addValue("salt", newSalt);
            anyThingToUpdate = true;
        }
        if (user.getTermAccepted() != null) {
            sql += " term_accepted = :termAccepted,";
            params.addValue("termAccepted", user.getTermAccepted());
            anyThingToUpdate = true;
        }
        if (user.getEmailValidated() != null) {
            sql += " email_validated = :emailValidated,";
            params.addValue("emailValidated", user.getEmailValidated());
            anyThingToUpdate = true;
        }

        if (anyThingToUpdate) {
            sql += " updated_at = current_timestamp WHERE id = :id";
            namedParameterJdbcTemplate.update(sql, params);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM users WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, params);
    }
}
