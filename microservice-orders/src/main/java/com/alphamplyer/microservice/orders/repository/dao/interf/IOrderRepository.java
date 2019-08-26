package com.alphamplyer.microservice.orders.repository.dao.interf;

import com.alphamplyer.microservice.orders.models.Order;

import java.util.List;

public interface IOrderRepository {

    /**
     * Get an order by an ID from database
     * @param id order ID
     * @return an order or null if not founded
     */
    Order getByID (Integer id);

    /**
     * Get all user's orders from its ID
     * @param id user ID
     * @return all user's orders
     */
    List<Order> getUserOrders (Integer id);

    /**
     * Add an order to the database
     * @param order order to add
     * @return the order added or null if error
     */
    Order add (Order order);

    /**
     * Update an order from the database
     * @param order order to update with new values and its ID (Order need ID, ID can't be null)
     */
    void save (Order order);

    /**
     * Delete an order from the database
     * @param id order ID
     */
    void delete (Integer id);
}
