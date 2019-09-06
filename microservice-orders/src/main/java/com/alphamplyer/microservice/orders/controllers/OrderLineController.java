package com.alphamplyer.microservice.orders.controllers;

import com.alphamplyer.microservice.orders.exceptions.*;
import com.alphamplyer.microservice.orders.models.OrderLine;
import com.alphamplyer.microservice.orders.repositories.dao.interf.IOrderLineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderLineController {

    private static final Logger logger = LoggerFactory.getLogger(OrderLineController.class);

    private IOrderLineRepository orderLineRepository;

    @Autowired
    public void setOrderLineRepository(IOrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    /**
     * Get Order line from its ID
     * @param id Order line ID
     * @return an order line or 404 error if not founded
     */
    @GetMapping(value = "orders/lines/{id}")
    public ResponseEntity<OrderLine> getByID(@PathVariable(name = "id") Integer id) {
        if (id == null || id < 0)
            throw new BadRequestException("Order line ID is null or lower than 0");

        OrderLine orderLine;

        try {
            orderLine = orderLineRepository.getByID(id);
        } catch (DataAccessException e) {
            logger.error("Order line not found in database", e);
            throw new NotFoundException(String.format("Order line not found (ID = %d)", id));
        }

        return new ResponseEntity<>(orderLine, HttpStatus.OK);
    }

    /**
     * Get order lines of an order
     * @param id order ID
     * @return list of order line or 404 error if not founded
     */
    @GetMapping(value = "orders/{id}/lines")
    public ResponseEntity<List<OrderLine>> getOrderLines(@PathVariable(name = "id") Integer id) {
        if (id == null || id < 0)
            throw new BadRequestException("User ID is null or lower than 0");

        List<OrderLine> orderLines;

        try {
            orderLines = orderLineRepository.getOrderLines(id);
        } catch (DataAccessException e) {
            logger.error("Order lines not found in database", e);
            throw new NotFoundException(String.format("Order lines not found (user ID = %d)", id));
        }

        return new ResponseEntity<>(orderLines, HttpStatus.OK);
    }

    /**
     * Add order line to an order
     * @param orderLine order line data
     * @return added order line or error 500
     */
    @PostMapping(value = "orders/line/add")
    public ResponseEntity<OrderLine> add (@RequestBody OrderLine orderLine) {
        OrderLine rOrderLine = null;

        try {
            rOrderLine = orderLineRepository.add(orderLine);
        } catch (DataAccessException e) {
            logger.error("Failed to insert order line", e);
        }

        if (rOrderLine == null) {
            throw new UnableToInsertException("Failed to insert order line !");
        }

        return new ResponseEntity<>(rOrderLine, HttpStatus.CREATED);
    }

    /**
     * Add an order line to an order
     * @param orderLines order lines data
     * @return added order line or error 500
     */
    @PostMapping(value = "orders/lines/add")
    public ResponseEntity<Void> add(@RequestBody List<OrderLine> orderLines) {
        try {
            orderLineRepository.add(orderLines);
        } catch (DataAccessException e) {
            logger.error("Failed to insert order line", e);
            throw new UnableToInsertException("Failed to insert order line !");
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Save an order line to an order
     * @param orderLine order lines data
     * @return OK httpCode line or error 500
     */
    @PutMapping(value = "orders/line/save")
    public ResponseEntity<Void> save (@RequestBody OrderLine orderLine) {
        try {
            orderLineRepository.save(orderLine);
        } catch (DataAccessException e) {
            logger.error("Failed to update order line in database", e);
            throw new UnableToUpdateException("Failed to update order line in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Delete an order line from its ID
     * @param id order lines ID
     * @return OK httpCode line or error 500
     */
    @DeleteMapping(value = "orders/line/{id}/delete")
    public ResponseEntity<Void> delete (@PathVariable(name = "id") Integer id) {
        if (id == null || id < 0)
            throw new BadRequestException("Order Line ID is null or lower than 0");

        try {
            orderLineRepository.delete(id);
        } catch (DataAccessException e) {
            logger.error("Failed to delete order line in database", e);
            throw new UnableToDeleteException("Failed to delete order line in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
