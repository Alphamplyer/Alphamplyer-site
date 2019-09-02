package com.alphamplyer.microservice.products.repositories.rowMappers;

import com.alphamplyer.microservice.products.models.Reduction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Row Mapper to deserialize result of database into Reduction object
 */
public class ReductionRowMapper implements RowMapper<Reduction> {

    @Override
    public Reduction mapRow(ResultSet rs, int i) throws SQLException {
        Reduction reduction = new Reduction();

        reduction.setId(rs.getInt("id"));
        reduction.setAmount(rs.getDouble("amount"));
        reduction.setActivationCode(rs.getString("activation_code"));
        reduction.setBeginDateTime(rs.getTimestamp("begin_date_time"));
        reduction.setEndDateTime(rs.getTimestamp("end_date_time"));

        return null;
    }
}
