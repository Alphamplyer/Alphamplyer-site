package com.alphamplyer.microservice.products.repositories.rowMappers;

import com.alphamplyer.microservice.products.models.ProductTypeReduction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTypeReductionRowMapper implements RowMapper<ProductTypeReduction> {

    @Override
    public ProductTypeReduction mapRow(ResultSet rs, int i) throws SQLException {
        ProductTypeReduction reduction = new ProductTypeReduction();

        reduction.setId(rs.getInt("id"));
        reduction.setProductTypeId(rs.getInt("type_id"));
        reduction.setReductionId(rs.getInt("reduction_id"));

        return reduction;
    }
}
