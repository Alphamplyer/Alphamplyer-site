package com.alphamplyer.microservice.orders.controllers;

import com.alphamplyer.microservice.orders.exceptions.*;
import com.alphamplyer.microservice.orders.models.Order;
import com.alphamplyer.microservice.orders.repository.dao.interf.IOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private IOrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Get Order from its ID
     * @param id order ID
     * @return order or 404 error if not founded
     */
    @GetMapping(value = "/orders/id/{id}")
    public ResponseEntity<Order> getOrderByID(@PathVariable(name = "id") Integer id) {
        if (id == null || id < 0)
            throw new BadRequestException("Order ID is null or lower than 0");

        Order order;

        try {
            order = orderRepository.getByID(id);
        } catch (DataAccessException e) {
            logger.error("Order not found in database", e);
            throw new NotFoundException(String.format("Order not found (ID = %d)", id));
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    /**
     * Get list of user order
     * @param id user ID
     * @return list of user order
     */
    @GetMapping(value = "/orders/user/{id}")
    public ResponseEntity<List<Order>> getUserOrders (@PathVariable(name = "id") Integer id) {

        if (id == null || id < 0)
            throw new BadRequestException("User ID is null or lower than 0");

        List<Order> orders;

        try {
            orders = orderRepository.getUserOrders(id);
        } catch (DataAccessException e) {
            logger.error("Orders not found in database", e);
            throw new NotFoundException(String.format("Orders not found (user ID = %d)", id));
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    /**
     * Add order
     * @param order order data
     * @return saved order or error 500
     */
    @PostMapping(value = "/orders/add")
    public ResponseEntity<Order> addOrder (Order order) {
        Order rOrder = null;

        try {
            rOrder = orderRepository.add(order);
        } catch (DataAccessException e) {
            logger.error("Failed to insert order", e);
        }

        if (rOrder == null) {
            throw new UnableToInsertException("Failed to insert order !");
        }

        return new ResponseEntity<>(rOrder, HttpStatus.CREATED);
    }


    /**
     * Update order
     * @param order order data
     * @return OK http status or internal error
     */
    @PutMapping(value = "/orders/save")
    public ResponseEntity<Void> saveOrder (Order order) {
        try {
            orderRepository.save(order);
        } catch (DataAccessException e) {
            logger.error("Failed to update order in database", e);
            throw new UnableToUpdateException("Failed to update order in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Delete order
     * @param id order ID
     * @return OK http status or internal error
     */
    @DeleteMapping(value = "/orders/delete/{id}")
    public ResponseEntity<Void> deleteOrder (@PathVariable(name = "id") Integer id) {
        if (id == null || id < 0)
            throw new BadRequestException("Order ID is null or lower than 0");

        try {
            orderRepository.delete(id);
        } catch (DataAccessException e) {
            logger.error("Failed to delete order in database", e);
            throw new UnableToDeleteException("Failed to delete order in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
