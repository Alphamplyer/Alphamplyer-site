package com.alphamplyer.microservice.products.repositories.rowMappers;

import com.alphamplyer.microservice.products.models.ProductType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Row Mapper to deserialize result of database into ProductType object
 */
public class ProductTypeRowMapper implements RowMapper<ProductType> {

    @Override
    public ProductType mapRow(ResultSet rs, int i) throws SQLException {
        ProductType productType = new ProductType();

        productType.setId(rs.getInt("id"));
        productType.setParentId(rs.getInt("parent_id"));
        productType.setCode(rs.getString("code"));
        productType.setDescription(rs.getString("description"));

        return productType;
    }
}
