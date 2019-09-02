package com.alphamplyer.microservice.orders.repository.rowMappers;

import com.alphamplyer.microservice.orders.models.OrderLine;
import com.alphamplyer.microservice.orders.models.enums.OrderLineStatus;
import com.alphamplyer.microservice.orders.models.enums.RenewalRate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Row Mapper to deserialize result of database into OrderLine object
 */
public class OrderLineRowMapper implements RowMapper<OrderLine> {

    @Override
    public OrderLine mapRow(ResultSet rs, int i) throws SQLException {
        OrderLine orderLine = new OrderLine();

        orderLine.setId(rs.getInt("id"));
        orderLine.setOrderId(rs.getInt("order_id"));
        orderLine.setProductId(rs.getInt("product_id"));
        orderLine.setStatus(OrderLineStatus.valueOf(rs.getString("status")));
        orderLine.setQuantity(rs.getInt("quantity"));
        orderLine.setPrice(rs.getDouble("price"));
        orderLine.setReductionAmount(rs.getDouble("reduction_amount"));
        orderLine.setRenewalRate(RenewalRate.valueOf(rs.getString("renewal_rate")));

        return orderLine;
    }
}
