package com.alphamplyer.microservice.products.repositories.dao.impl;

import com.alphamplyer.microservice.products.models.Product;
import com.alphamplyer.microservice.products.repositories.dao.DAORepository;
import com.alphamplyer.microservice.products.repositories.dao.interf.IProductRepository;
import com.alphamplyer.microservice.products.repositories.rowMappers.ProductRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Repository
public class ProductRepository extends DAORepository implements IProductRepository {

    @Override
    public Product getByID(Integer id, Boolean includeNoAvailableToPublic, Boolean includeNoAvailable) {
        String sql = "SELECT * FROM products WHERE id = :id"
            + buildSQLCondition(!includeNoAvailableToPublic, " AND available_from < NOW()")
            + buildSQLCondition(!includeNoAvailable, " AND status <> 'NO_AVAILABLE'");

        RowMapper<Product> rowMapper = new ProductRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        Product product;

        try {
            product = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return product;
    }

    @Override
    public Product getByCode(String code, Boolean includeNoAvailableToPublic, Boolean includeNoAvailable) {
        String sql = "SELECT * FROM products WHERE code = :code"
            + buildSQLCondition(!includeNoAvailableToPublic, " AND available_from < NOW()")
            + buildSQLCondition(!includeNoAvailable, " AND status <> 'NO_AVAILABLE'");

        RowMapper<Product> rowMapper = new ProductRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("code", code);

        Product product;

        try {
            product = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return product;
    }

    @Override
    public List<Product> getAll(Integer offset, Integer limit, Boolean includeNoAvailableToPublic, Boolean includeNoAvailable) {

        String sql = "SELECT * FROM products ";

        boolean whereCloseAdded = false;
        if (!includeNoAvailableToPublic) {
            whereCloseAdded = true;
            sql += "WHERE available_from < NOW() ";
        }
        if (!includeNoAvailable) {
            if (whereCloseAdded) {
                sql += "AND status <> 'NO_AVAILABLE' ";
            }
            else {
                sql += "WHERE status <> 'NO_AVAILABLE'";
            }
        }
        sql += "ORDER BY name " + buildSQLOffsetLimit(offset, limit);

        RowMapper<Product> rowMapper = new ProductRowMapper();

        List<Product> productList = jdbcTemplate.query(sql, rowMapper);

        if (productList.isEmpty())
            return null;

        return productList;
    }

    @Override
    public List<Product> getAllByType(Integer type_id, Integer offset, Integer limit, Boolean includeNoAvailableToPublic, Boolean includeNoAvailable) {
        String sql = "WITH RECURSIVE type_collection AS ( " +
            "SELECT id, parent_id " +
            "FROM product_types " +
            "WHERE id = :type_id " +
            "UNION ALL " +
            "SELECT pt.id, pt.parent_id " +
            "FROM product_types AS pt " +
            "JOIN type_collection ON type_collection.id = pt.parent_id " +
            ") " +
            "SELECT p.* FROM products p, type_collection tc WHERE p.type_id = tc.id "
            + buildSQLCondition(!includeNoAvailableToPublic, " AND available_from < NOW()")
            + buildSQLCondition(!includeNoAvailable, " AND status <> 'NO_AVAILABLE'")
            + " ORDER BY p.name "
            + buildSQLOffsetLimit(offset, limit);

        RowMapper<Product> rowMapper = new ProductRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("type_id", type_id);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, params, rowMapper);

        if (productList.isEmpty())
            return null;

        return productList;
    }

    @Override
    public Product add(Product product) {
        String sql = "INSERT INTO products (code, name, description, price, unit_by_user, renewal, game_content, status, image_id, available_from, available_to, type_id) " +
            "VALUES (:code, :name, :description, :price, :unit_by_user, :renewal, :game_content, :status, :image_id, :available_from, :available_to, :type_id)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("code", product.getCode());
        params.addValue("type_id", product.getTypeId());
        params.addValue("name", product.getName());
        params.addValue("description", product.getDescription());
        params.addValue("price", product.getPrice());
        params.addValue("unit_by_user", product.getUnitByUser());
        params.addValue("renewal", product.getRenewal());
        params.addValue("game_content", product.getGameContent());
        params.addValue("status", product.getStatus());
        params.addValue("image_id", product.getImage_id());
        params.addValue("available_from", product.getAvailableFrom());
        params.addValue("available_to", product.getAvailableTo());

        namedParameterJdbcTemplate.update(sql, params, keyHolder);

        HashMap<String, Object> keys;

        if (keyHolder.getKeys() != null)
            keys = new HashMap<String, Object>(keyHolder.getKeys());
        else
            return null;

        product.setId((Integer)keys.get("id"));
        product.setAvailableFrom((Timestamp)keys.get("available_from"));
        product.setAvailableTo((Timestamp)keys.get("available_to"));

        return product;
    }

    @Override
    public void save(Product product) {
        String sql = "UPDATE products SET code = :code, type_id = :type_id, name = :name, description = :description, price = :price, " +
            "unit_by_user = :unit_by_user, renewal = :renewal, game_content = :game_content, status = :status, image_id = :image_id, " +
            "available_from = :available_from, available_to = :available_to WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", product.getId());
        params.addValue("code", product.getCode());
        params.addValue("type_id", product.getTypeId());
        params.addValue("name", product.getName());
        params.addValue("description", product.getDescription());
        params.addValue("price", product.getPrice());
        params.addValue("unit_by_user", product.getUnitByUser());
        params.addValue("renewal", product.getRenewal());
        params.addValue("game_content", product.getGameContent());
        params.addValue("status", product.getStatus());
        params.addValue("image_id", product.getImage_id());
        params.addValue("available_from", product.getAvailableFrom());
        params.addValue("available_to", product.getAvailableTo());

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM products WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, params);
    }
}
