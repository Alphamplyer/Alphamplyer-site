package com.alphamplyer.microservice.users.repositories.dao.impl;

import com.alphamplyer.microservice.users.models.VerificationToken;
import com.alphamplyer.microservice.users.repositories.dao.DAORepository;
import com.alphamplyer.microservice.users.repositories.dao.interf.IVerificationTokenRepository;
import com.alphamplyer.microservice.users.repositories.rowMappers.VerificationTokenRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class VerificationTokenRepository extends DAORepository implements IVerificationTokenRepository {

    @Override
    public VerificationToken getVerificationToken(String token) {
        String sql = "SELECT * FROM verification_tokens WHERE token = :token ";

        RowMapper<VerificationToken> rowMapper = new VerificationTokenRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("token", token);

        return namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
    }

    @Override
    public void save(VerificationToken verificationToken) {
        String sql = "INSERT INTO verification_tokens (token, user_id, expiry_date_time) VALUES (:token, :user_id, :expiry_date_time)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("token", verificationToken.getToken());
        params.addValue("user_id", verificationToken.getUserID());
        params.addValue("expiry_date_time", verificationToken.getExpiryDateTime());

        namedParameterJdbcTemplate.update(sql, params);
    }
}
