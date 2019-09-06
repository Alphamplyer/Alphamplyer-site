package com.alphamplyer.microservice.orders.repositories.dao.interf;

import com.alphamplyer.microservice.orders.models.OrderLine;

import java.util.List;

public interface IOrderLineRepository {

    /**
     * Get an order line from database with an ID.
     * @param id order line id
     * @return an order line or null if not founded
     */
    OrderLine getByID(Integer id);

    /**
     * Get all order lines of an order with his id
     * @param id order id
     * @return all order lines
     */
    List<OrderLine> getOrderLines(Integer id);

    /**
     * Insert an order line to the database
     * @param orderLine order line to insert
     * @return order line added or null if error
     */
    OrderLine add (OrderLine orderLine);

    /**
     * Insert multiple order line to the database
     * @param orderLines list of order line to add
     */
    void add(List<OrderLine> orderLines);

    /**
     * Update an order line from the database
     * @param orderLine order line to update with id and its values (Order line need ID, ID can't be null)
     */
    void save (OrderLine orderLine);

    /**
     * Delete an order line from the database
     * @param id order line ID
     */
    void delete (Integer id);
}
