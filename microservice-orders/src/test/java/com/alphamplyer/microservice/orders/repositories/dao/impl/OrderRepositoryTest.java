package com.alphamplyer.microservice.orders.repositories.dao.impl;

import com.alphamplyer.microservice.orders.ResetDatabase;
import com.alphamplyer.microservice.orders.models.Order;
import com.alphamplyer.microservice.orders.models.enums.OrderStatus;
import com.alphamplyer.microservice.orders.repositories.dao.interf.IOrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderRepositoryTest {

    @Autowired
    IOrderRepository orderRepository;

    private Order prepareOrder() {
        Order order = new Order();

        order.setUserId(1);
        order.setCreatorId(1);
        order.setPaymentId(5);

        return order;
    }

    @BeforeAll
    static void resetDatabase() {
        try {
            ResetDatabase.reset("src/test/resources/resetOrdersTestDatabase.sql");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("========= FAILED RESET DATABASE ==========");
            e.printStackTrace();
        }
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void getByID() {
        Order order = orderRepository.getByID(1);
        assertNotNull(order);
        assertEquals(1, order.getUserId());
        assertEquals(1, order.getPaymentId());

        order = orderRepository.getByID(2);
        assertNotNull(order);
        assertEquals(1, order.getUserId());
        assertEquals(2, order.getPaymentId());

        order = orderRepository.getByID(3);
        assertNotNull(order);
        assertEquals(2, order.getUserId());
        assertEquals(3, order.getPaymentId());

        order = orderRepository.getByID(4);
        assertNotNull(order);
        assertEquals(3, order.getUserId());
        assertEquals(4, order.getPaymentId());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void getUserOrders() {
        List<Order> orders = orderRepository.getUserOrders(1);
        assertNotNull(orders);
        assertEquals(2, orders.size());
        assertEquals(1, orders.get(0).getPaymentId());
        assertEquals(2, orders.get(1).getPaymentId());

        orders = orderRepository.getUserOrders(2);
        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals(3, orders.get(0).getPaymentId());

        orders = orderRepository.getUserOrders(3);
        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals(4, orders.get(0).getPaymentId());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void add() {
        Order order = prepareOrder();
        Order db_order = orderRepository.add(order);
        assertNotNull(db_order);
        assertNotNull(db_order.getId());
        assertEquals(order.getUserId(), db_order.getUserId());
        assertEquals(order.getCreatorId(), db_order.getCreatorId());
        assertEquals(order.getPaymentId(), db_order.getPaymentId());
        assertEquals(OrderStatus.PENDING, db_order.getStatus());
        assertNotNull(db_order.getCreatedAt());
        assertNotNull(db_order.getUpdatedAt());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void save() {
        Order order = prepareOrder();

        order.setId(5);
        order.setPaymentId(5);
        order.setStatus(OrderStatus.COMPLETE);

        orderRepository.save(order);
        Order db_order = orderRepository.getByID(5);

        assertNotNull(db_order);
        assertEquals(order.getPaymentId(), db_order.getPaymentId());
        assertEquals(OrderStatus.COMPLETE, db_order.getStatus());
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void delete() {
        orderRepository.delete(5);
        Order order = orderRepository.getByID(5);
        assertNull(order);
    }
}