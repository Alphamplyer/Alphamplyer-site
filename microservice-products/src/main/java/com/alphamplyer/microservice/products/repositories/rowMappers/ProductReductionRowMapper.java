package com.alphamplyer.microservice.products.repositories.rowMappers;

import com.alphamplyer.microservice.products.models.ProductReduction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Row Mapper to deserialize result of database into ProductReduction object
 */
public class ProductReductionRowMapper implements RowMapper<ProductReduction> {

    @Override
    public ProductReduction mapRow(ResultSet rs, int i) throws SQLException {
        ProductReduction reduction = new ProductReduction();

        reduction.setId(rs.getInt("id"));
        reduction.setProductId(rs.getInt("product_id"));
        reduction.setReductionId(rs.getInt("reduction_id"));

        return reduction;
    }
}
