package com.alphamplyer.microservice.orders.repositories.dao.impl;

import com.alphamplyer.microservice.orders.ResetDatabase;
import com.alphamplyer.microservice.orders.models.OrderLine;
import com.alphamplyer.microservice.orders.models.enums.OrderLineStatus;
import com.alphamplyer.microservice.orders.models.enums.RenewalRate;
import com.alphamplyer.microservice.orders.repositories.dao.interf.IOrderLineRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderLineRepositoryTest {

    @Autowired
    private IOrderLineRepository orderLineRepository;

    private OrderLine prepareOrderLine() {
        OrderLine orderLine = new OrderLine();

        orderLine.setOrderId(1);
        orderLine.setProductId(1);
        orderLine.setQuantity(1);
        orderLine.setPrice(4.0);
        orderLine.setReductionAmount(1.0);
        orderLine.setStatus(OrderLineStatus.IN_PREPARATION);
        orderLine.setRenewalRate(RenewalRate.NONE);

        return orderLine;
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
    @Order(1)
    void getByID() {
        OrderLine orderLine = orderLineRepository.getByID(1);
        assertNotNull(orderLine);
        assertEquals(1, orderLine.getOrderId());
        assertEquals(4, orderLine.getPrice());

        orderLine = orderLineRepository.getByID(2);
        assertNotNull(orderLine);
        assertEquals(1, orderLine.getOrderId());
        assertEquals(5, orderLine.getPrice());

        orderLine = orderLineRepository.getByID(3);
        assertNotNull(orderLine);
        assertEquals(1, orderLine.getOrderId());
        assertEquals(2.5, orderLine.getPrice());

        orderLine = orderLineRepository.getByID(4);
        assertNotNull(orderLine);
        assertEquals(2, orderLine.getOrderId());
        assertEquals(4, orderLine.getPrice());
    }

    @Test
    @Order(2)
    void getOrderLines() {
        List<OrderLine> orderLines = orderLineRepository.getOrderLines(1);
        assertNotNull(orderLines);

        System.out.println(orderLines.size());

        for (OrderLine ol : orderLines) {
            System.out.println(ol.toString());
        }

        assertEquals(3, orderLines.size());
        assertEquals(2, orderLines.get(0).getQuantity());
        assertEquals(1, orderLines.get(1).getQuantity());
        assertEquals(1, orderLines.get(2).getQuantity());

        orderLines = orderLineRepository.getOrderLines(2);
        assertNotNull(orderLines);
        assertEquals(2, orderLines.size());
        assertEquals(3, orderLines.get(0).getQuantity());
        assertEquals(2, orderLines.get(1).getQuantity());


        orderLines = orderLineRepository.getOrderLines(3);
        assertNotNull(orderLines);
        assertEquals(3, orderLines.size());
        assertEquals(1, orderLines.get(0).getQuantity());
        assertEquals(1, orderLines.get(1).getQuantity());
        assertEquals(2, orderLines.get(2).getQuantity());


        orderLines = orderLineRepository.getOrderLines(4);
        assertNotNull(orderLines);
        assertEquals(2, orderLines.size());
        assertEquals(1, orderLines.get(0).getQuantity());
        assertEquals(1, orderLines.get(1).getQuantity());

    }

    @Test
    @Order(3)
    void add() {
        OrderLine orderLine = prepareOrderLine();
        OrderLine db_orderLine = orderLineRepository.add(orderLine);
        assertNotNull(db_orderLine);

        assertNotNull(db_orderLine.getId());
        assertEquals(orderLine.getOrderId(), db_orderLine.getOrderId());
        assertEquals(orderLine.getProductId(), db_orderLine.getProductId());
        assertEquals(orderLine.getQuantity(), db_orderLine.getQuantity());
        assertEquals(orderLine.getPrice(), db_orderLine.getPrice());
        assertEquals(OrderLineStatus.IN_PREPARATION, db_orderLine.getStatus());
        assertEquals(RenewalRate.NONE, db_orderLine.getRenewalRate());
    }

    @Test
    @Order(4)
    void addList() {
        List<OrderLine> orderLines = new ArrayList<>();

        OrderLine orderLine1 = prepareOrderLine();
        orderLine1.setQuantity(2);

        OrderLine orderLine2 = prepareOrderLine();
        orderLine2.setQuantity(3);

        OrderLine orderLine3 = prepareOrderLine();
        orderLine3.setQuantity(4);

        orderLines.add(orderLine1);
        orderLines.add(orderLine2);
        orderLines.add(orderLine3);

        orderLineRepository.add(orderLines);

        List<OrderLine> db_orderLines = orderLineRepository.getOrderLines(1);
        assertNotNull(db_orderLines);

        assertEquals(2, db_orderLines.get(4).getQuantity());
        assertEquals(3, db_orderLines.get(5).getQuantity());
        assertEquals(4, db_orderLines.get(6).getQuantity());
    }

    @Test
    @Order(5)
    void save() {
        OrderLine orderLine = prepareOrderLine();
        orderLine.setId(11);
        orderLine.setQuantity(10);
        orderLine.setStatus(OrderLineStatus.DELIVERED);
        orderLine.setPrice(3.0);
        orderLine.setReductionAmount(0.5); // 50% du prix

        orderLineRepository.save(orderLine);
        OrderLine db_orderLine = orderLineRepository.getByID(11);
        assertNotNull(db_orderLine);

        assertEquals(orderLine.getQuantity(), db_orderLine.getQuantity());
        assertEquals(orderLine.getStatus(), db_orderLine.getStatus());
        assertEquals(orderLine.getPrice(), db_orderLine.getPrice());
        assertEquals(orderLine.getReductionAmount(), db_orderLine.getReductionAmount());
    }

    @Test
    @Order(6)
    void delete() {
        orderLineRepository.delete(11);
        orderLineRepository.delete(12);
        orderLineRepository.delete(13);
        orderLineRepository.delete(14);
        OrderLine orderLine = orderLineRepository.getByID(11);
        assertNull(orderLine);
    }
}