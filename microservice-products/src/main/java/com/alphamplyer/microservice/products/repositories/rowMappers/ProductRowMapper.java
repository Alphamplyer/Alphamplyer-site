package com.alphamplyer.microservice.products.repositories.rowMappers;

import com.alphamplyer.microservice.products.models.Product;
import com.alphamplyer.microservice.products.models.ProductStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {
        Product product = new Product();

        product.setId(rs.getInt("id"));
        product.setTypeId(rs.getInt("type_id"));
        product.setCode(rs.getString("code"));

        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));

        product.setUnitByUser(rs.getInt("unit_by_user"));
        product.setRenewal(rs.getBoolean("renewal"));
        product.setGameContent(rs.getBoolean("game_content"));
        product.setStatus(ProductStatus.valueOf(rs.getString("status")));

        product.setImage_id(rs.getInt("image_id"));

        product.setAvailableFrom(rs.getTimestamp("available_from"));
        product.setAvailableTo(rs.getTimestamp("available_to"));

        return product;
    }
}
