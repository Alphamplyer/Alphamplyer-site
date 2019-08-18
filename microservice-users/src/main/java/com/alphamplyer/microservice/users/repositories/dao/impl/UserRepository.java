package com.alphamplyer.microservice.users.repositories.dao.impl;

import com.alphamplyer.microservice.users.models.User;
import com.alphamplyer.microservice.users.repositories.dao.DAORepository;
import com.alphamplyer.microservice.users.repositories.dao.interf.IUserRepository;
import com.alphamplyer.microservice.users.repositories.rowMappers.UserRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

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
        String sql = "SELECT * FROM users WHERE email = :email";

        RowMapper<User> rowMapper = new UserRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);

        User user;

        try {
            user = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return user;
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
    public User insert(User user) {
        String sql = "INSERT INTO users (username, email, password, salt, birth_date, update_at, created_at) VALUES (:username, :email, :password, :salt, :birth_date, :updated_at, :created_at)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", user.getUsername());
        params.addValue("email", user.getEmail());
        params.addValue("password", user.getPassword());
        params.addValue("salt", user.getSalt());
        params.addValue("birth_date", user.getBirthDate());
        params.addValue("updated_at", now);
        params.addValue("created_at", now);

        namedParameterJdbcTemplate.update(sql, params, keyHolder);

        if (keyHolder.getKey() == null)
            return null;

        user.setId(keyHolder.getKey().intValue());
        user.setRoleId(0);
        user.setTermAccepted(false);
        user.setEmailValidated(false);
        user.setUpdatedAt(now);
        user.setCreatedAt(now);

        return user;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET role_id = :roleId, username = :username, email = :email, " +
            "password = :password, salt = :salt, " +
            "term_accepted = :termAccepted, email_validated = :emailValidated, " +
            "update_at = current_timestamp WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", user.getId());

        if (user.getRoleId() != null) params.addValue("roleId", user.getRoleId());
        if (user.getUsername() != null) params.addValue("username", user.getUsername());
        if (user.getEmail() != null) params.addValue("email", user.getEmail());
        if (user.getPassword() != null) params.addValue("password", user.getPassword());
        if (user.getSalt() != null) params.addValue("salt", user.getSalt());
        if (user.getTermAccepted() != null) params.addValue("termAccepted", user.getTermAccepted());
        if (user.getEmailValidated() != null) params.addValue("emailValidated", user.getEmailValidated());

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM users WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, params);
    }
}
