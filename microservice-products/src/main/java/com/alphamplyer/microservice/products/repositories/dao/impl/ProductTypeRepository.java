package com.alphamplyer.microservice.products.repositories.dao.impl;

import com.alphamplyer.microservice.products.models.ProductType;
import com.alphamplyer.microservice.products.repositories.dao.DAORepository;
import com.alphamplyer.microservice.products.repositories.dao.interf.IProductTypeRepository;
import com.alphamplyer.microservice.products.repositories.rowMappers.ProductTypeRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ProductTypeRepository extends DAORepository implements IProductTypeRepository {

    @Override
    public ProductType getByID(Integer id) {
        String sql = "SELECT * FROM product_types WHERE id = :id";

        RowMapper<ProductType> rowMapper = new ProductTypeRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        ProductType productType;

        try {
            productType = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return productType;
    }

    @Override
    public ProductType getParent(Integer id) {
        String sql = "SELECT p.* FROM product_types p, product_types p2 WHERE p.id = p2.parent_id AND p2.id = :id";

        RowMapper<ProductType> rowMapper = new ProductTypeRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        ProductType productType;

        try {
            productType = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return productType;
    }

    @Override
    public List<ProductType> getChilds(Integer id) {
        String sql = "SELECT * FROM product_types WHERE parent_id = :id";

        RowMapper<ProductType> rowMapper = new ProductTypeRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        List<ProductType> productTypes = namedParameterJdbcTemplate.query(sql, params, rowMapper);

        if (productTypes.isEmpty())
            return null;

        return productTypes;
    }

    @Override
    public List<ProductType> getMainProductType() {
        String sql = "SELECT * FROM product_types WHERE parent_id IS NULL";

        RowMapper<ProductType> rowMapper = new ProductTypeRowMapper();

        List<ProductType> productTypes = jdbcTemplate.query(sql, rowMapper);

        if (productTypes.isEmpty())
            return null;

        return productTypes;
    }

    @Override
    public ProductType add(ProductType productType) {
        String sql = "INSERT INTO product_types (parent_id, code, name, description) " +
            "VALUES (:parent_id, :code, :name, :description)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("parent_id", productType.getParentId());
        params.addValue("code", productType.getCode());
        params.addValue("name", productType.getName());
        params.addValue("description", productType.getDescription());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, params, keyHolder);

        HashMap<String, Object> keys;

        if (keyHolder.getKeys() != null)
            keys = new HashMap<String, Object>(keyHolder.getKeys());
        else
            return null;

        productType.setId((Integer)keys.get("id"));

        return productType;
    }

    @Override
    public void save(ProductType productType) {
        String sql = "UPDATE product_types " +
            "SET parent_id = :parent_id, code = :code, name = :name, description = :description " +
            "WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", productType.getId());
        params.addValue("parent_id", productType.getParentId());
        params.addValue("code", productType.getCode());
        params.addValue("name", productType.getName());
        params.addValue("description", productType.getDescription());

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM product_types WHERE id = :id";

        MapSqlParameterSource params_news = new MapSqlParameterSource();
        params_news.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, params_news);
    }
}
