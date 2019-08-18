package com.alphamplyer.microservice.users.repositories.rowMappers;

import com.alphamplyer.microservice.users.models.User;
import com.alphamplyer.microservice.users.models.VerificationToken;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VerificationTokenRowMapper implements RowMapper<VerificationToken> {

    @Override
    public VerificationToken mapRow(ResultSet rs, int rowNum) throws SQLException {
        VerificationToken verificationToken = new VerificationToken();

        verificationToken.setId(rs.getInt("id"));
        verificationToken.setToken(rs.getString("token"));
        verificationToken.setUserID(rs.getInt("user_id"));
        verificationToken.setExpiryDateTime(rs.getTimestamp("expiry_date_time"));

        return verificationToken;
    }
}
