package com.alphamplyer.microservice.orders.repositories.dao.impl;

import com.alphamplyer.microservice.orders.models.Order;
import com.alphamplyer.microservice.orders.models.enums.OrderStatus;
import com.alphamplyer.microservice.orders.repositories.dao.DAORepository;
import com.alphamplyer.microservice.orders.repositories.dao.interf.IOrderRepository;
import com.alphamplyer.microservice.orders.repositories.rowMappers.OrderRowMapper;
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
public class OrderRepository extends DAORepository implements IOrderRepository {

    @Override
    public Order getByID(Integer id) {
        String sql = "SELECT * FROM orders WHERE id = :id";

        RowMapper<Order> rowMapper = new OrderRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        Order order;

        try {
            order = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return order;
    }

    @Override
    public List<Order> getUserOrders(Integer id) {
        String sql = "SELECT * FROM orders WHERE user_id = :id";

        RowMapper<Order> rowMapper = new OrderRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        List<Order> orders = namedParameterJdbcTemplate.query(sql, params, rowMapper);

        if (orders.isEmpty())
            return null;

        return orders;
    }

    @Override
    public Order add(Order order) {
        String sql = "INSERT INTO orders (user_id, creator_id, payment_id) " +
            "VALUES (:user_id, :creator_id, :payment_id)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", order.getUserId());
        params.addValue("creator_id", order.getCreatorId());
        params.addValue("payment_id", order.getPaymentId());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, params, keyHolder);

        HashMap<String, Object> keys;

        if (keyHolder.getKeys() != null)
            keys = new HashMap<String, Object>(keyHolder.getKeys());
        else
            return null;

        order.setId((Integer)keys.get("id"));
        order.setStatus(OrderStatus.valueOf((String)keys.get("status")));
        order.setCreatedAt((Timestamp)keys.get("created_at"));

        return order;
    }

    @Override
    public void save(Order order) {
        String sql = "UPDATE orders " +
            "SET status = :status, payment_id = :payment_id, updated_at = NOW() " +
            "WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", order.getId());
        params.addValue("status", order.getStatus().name());
        params.addValue("payment_id", order.getPaymentId());

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM orders WHERE id = :id";

        MapSqlParameterSource params_news = new MapSqlParameterSource();
        params_news.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, params_news);
    }
}
