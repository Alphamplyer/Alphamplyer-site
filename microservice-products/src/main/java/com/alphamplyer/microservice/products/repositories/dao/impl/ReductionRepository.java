package com.alphamplyer.microservice.products.repositories.dao.impl;

import com.alphamplyer.microservice.products.models.Reduction;
import com.alphamplyer.microservice.products.repositories.dao.DAORepository;
import com.alphamplyer.microservice.products.repositories.dao.interf.IReductionRepository;
import com.alphamplyer.microservice.products.repositories.rowMappers.ReductionRowMapper;
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
public class ReductionRepository extends DAORepository implements IReductionRepository {

    @Override
    public Reduction getByID(Integer id) {
        String sql = "SELECT * FROM reductions WHERE id = :id";

        RowMapper<Reduction> rowMapper = new ReductionRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        Reduction reduction;

        try {
            reduction = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return reduction;
    }

    @Override
    public List<Reduction> getProductReduction(Integer productId) {
        String sql = "SELECT r.* " +
            "FROM reductions r, product_reduction pr, product_type_reduction ptr, products p " +
            "WHERE ((pr.product_id = p.id AND pr.reduction_id = r.id) OR (ptr.type_id = p.type_id AND ptr.reduction_id = r.id)) " +
            "AND p.id = :id";

        RowMapper<Reduction> rowMapper = new ReductionRowMapper();

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Reduction> getProductByTypeReduction(Integer type_id) {
        String sql = "SELECT r.* " +
            "FROM reductions r, product_type_reduction ptr, products p " +
            "WHERE ptr.type_id = p.type_id AND ptr.reduction_id = r.id AND p.id = :id";

        RowMapper<Reduction> rowMapper = new ReductionRowMapper();

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Reduction add(Reduction reduction) {
        String sql = "INSERT INTO reductions (amount, activation_code, begin_date_time, end_date_time) " +
            "VALUES (:amount, :activation_code, :begin_date_time, :end_date_time) ";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp b_d_t = reduction.getBeginDateTime();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("amount", reduction.getAmount());
        params.addValue("activation_code", reduction.getActivationCode());
        params.addValue("begin_date_time", b_d_t != null ? b_d_t : now); // if begin_date_time is null, set now()
        params.addValue("end_date_time", reduction.getEndDateTime());

        namedParameterJdbcTemplate.update(sql, params, keyHolder);

        HashMap<String, Object> keys;

        if (keyHolder.getKeys() != null)
            keys = new HashMap<String, Object>(keyHolder.getKeys());
        else
            return null;

        reduction.setId((Integer)keys.get("id"));
        reduction.setBeginDateTime((Timestamp)keys.get("begin_date_time"));
        reduction.setBeginDateTime((Timestamp)keys.get("end_date_time"));
        
        return reduction;
    }

    @Override
    public void deleteOldAndSaveNewPReduction (List<Integer> ProductIDs, Integer reduction_id) {
        StringBuilder sql = new StringBuilder("DELETE FROM product_reduction WHERE reduction_id = ")
            .append(reduction_id)
            .append("; INSERT INTO product_reduction (product_id, reduction_id) VALUES ");

        for (int i = 0; i < ProductIDs.size(); i++) {
            if (i != 0) {
                sql.append(", ");
            }
            sql.append("(").append(ProductIDs.get(i)).append(", ").append(reduction_id).append(")");
        }

        jdbcTemplate.update(sql.toString());
    }

    @Override
    public void deleteOldAndSaveNewPTypeReduction (List<Integer> ProductTypeIDs, Integer reduction_id) {
        StringBuilder sql = new StringBuilder("DELETE FROM product_type_reduction WHERE reduction_id = ")
            .append(reduction_id)
            .append("; INSERT INTO product_type_reduction (type_id, reduction_id) VALUES ");

        for (int i = 0; i < ProductTypeIDs.size(); i++) {
            if (i != 0) {
                sql.append(", ");
            }
            sql.append("(").append(ProductTypeIDs.get(i)).append(", ").append(reduction_id).append(")");
        }

        jdbcTemplate.update(sql.toString());
    }

    @Override
    public void save(Reduction reduction) {
        String sql = "UPDATE reductions SET ";

        MapSqlParameterSource params = new MapSqlParameterSource();

        boolean anyThingToUpdate = false;

        if (reduction.getAmount() != null) {
            sql += "amount = :amount";
            params.addValue("amount", reduction.getAmount());
            anyThingToUpdate = true;
        }

        if (anyThingToUpdate) sql+= ", ";

        if (reduction.getActivationCode() != null) {
            sql += "activation_code = :activation_code";
            params.addValue("activation_code", reduction.getActivationCode());
            anyThingToUpdate = true;
        }

        if (anyThingToUpdate) sql+= ", ";

        if (reduction.getBeginDateTime() != null) {
            sql += "begin_date_time = :begin_date_time";
            params.addValue("begin_date_time", reduction.getBeginDateTime());
            anyThingToUpdate = true;
        }

        if (anyThingToUpdate) sql+= ", ";

        if (reduction.getEndDateTime() != null) {
            sql += "end_date_time = :end_date_time";
            params.addValue("end_date_time", reduction.getEndDateTime());
            anyThingToUpdate = true;
        }

        if (anyThingToUpdate) {
            sql += " WHERE id = :id";
            params.addValue("id", reduction.getId());
            namedParameterJdbcTemplate.update(sql, params);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM reductions WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, params);
    }
}
