package com.alphamplyer.microservice.orders.repositories.dao.impl;

import com.alphamplyer.microservice.orders.models.OrderLine;
import com.alphamplyer.microservice.orders.repositories.dao.DAORepository;
import com.alphamplyer.microservice.orders.repositories.dao.interf.IOrderLineRepository;
import com.alphamplyer.microservice.orders.repositories.rowMappers.OrderLineRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class OrderLineRepository extends DAORepository implements IOrderLineRepository {

    @Override
    public OrderLine getByID(Integer id) {
        String sql = "SELECT * FROM order_lines WHERE id = :id";

        RowMapper<OrderLine> rowMapper = new OrderLineRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        OrderLine orderLine;

        try {
            orderLine = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return orderLine;
    }

    @Override
    public List<OrderLine> getOrderLines(Integer id) {
        String sql = "SELECT * FROM order_lines WHERE order_id = :id";

        RowMapper<OrderLine> rowMapper = new OrderLineRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        List<OrderLine> orderLines = namedParameterJdbcTemplate.query(sql, params, rowMapper);

        if (orderLines.isEmpty())
            return null;

        return orderLines;
    }

    @Override
    public OrderLine add(OrderLine orderLine) {
        String sql = "INSERT INTO order_lines (order_id, product_id, status, quantity, price, reduction_amount, renewal_rate) " +
            "VALUES (:order_id, :product_id, :status, :quantity, :price, :reduction_amount, :renewal_rate)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("order_id", orderLine.getOrderId());
        params.addValue("product_id", orderLine.getProductId());
        params.addValue("status", orderLine.getStatus().name());
        params.addValue("quantity", orderLine.getQuantity());
        params.addValue("price", orderLine.getPrice());
        params.addValue("reduction_amount", orderLine.getReductionAmount());
        params.addValue("renewal_rate", orderLine.getRenewalRate().name());

        namedParameterJdbcTemplate.update(sql, params, keyHolder);

        HashMap<String, Object> keys;

        if (keyHolder.getKeys() != null)
            keys = new HashMap<String, Object>(keyHolder.getKeys());
        else
            return null;

        orderLine.setId((Integer) keys.get("id"));

        return orderLine;
    }

    @Override
    public void add(List<OrderLine> orderLines) {
        StringBuilder sql = new StringBuilder("INSERT INTO order_lines (order_id, product_id, status, quantity, price, reduction_amount, renewal_rate) VALUES ");

        MapSqlParameterSource params = new MapSqlParameterSource();

        for (int i = 0; i < orderLines.size(); i++) {
            if (i != 0) {
                sql.append(", ");
            }
            sql.append("(")
                .append(":order_id_").append(i).append(", ")
                .append(":product_id_").append(i).append(", ")
                .append(":status_").append(i).append(", ")
                .append(":quantity_").append(i).append(", ")
                .append(":price_").append(i).append(", ")
                .append(":reduction_amount_").append(i).append(", ")
                .append(":renewal_rate_").append(i)
            .append(")");

            params.addValue("order_id_" + i, orderLines.get(i).getOrderId());
            params.addValue("product_id_" + i, orderLines.get(i).getProductId());
            params.addValue("status_" + i, orderLines.get(i).getStatus().name());
            params.addValue("quantity_" + i, orderLines.get(i).getQuantity());
            params.addValue("price_" + i, orderLines.get(i).getPrice());
            params.addValue("reduction_amount_" + i, orderLines.get(i).getReductionAmount());
            params.addValue("renewal_rate_" + i, orderLines.get(i).getRenewalRate().name());
        }

        namedParameterJdbcTemplate.update(sql.toString(), params);
    }

    @Override
    public void save(OrderLine orderLine) {
        String sql = "UPDATE order_lines " +
            "SET status = :status, quantity = :quantity, price = :price, " +
            "reduction_amount = :reduction_amount " +
            "WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", orderLine.getId());
        params.addValue("status", orderLine.getStatus().name());
        params.addValue("quantity", orderLine.getQuantity());
        params.addValue("price", orderLine.getPrice());
        params.addValue("reduction_amount", orderLine.getReductionAmount());

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM order_lines WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, params);
    }
}
