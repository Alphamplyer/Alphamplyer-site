package com.alphamplyer.microservice.products.repositories.dao.impl;

import com.alphamplyer.microservice.products.ResetDatabase;
import com.alphamplyer.microservice.products.models.Reduction;
import com.alphamplyer.microservice.products.repositories.dao.interf.IReductionRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReductionRepositoryTest {

    @Autowired
    private IReductionRepository reductionRepository;

    private Reduction prepareReduction() {
        Reduction reduction = new Reduction();

        reduction.setAmount(0.80);
        reduction.setActivationCode(null);
        reduction.setBeginDateTime(new Timestamp(new GregorianCalendar(2019, Calendar.SEPTEMBER, 1).getTimeInMillis()));
        reduction.setEndDateTime(new Timestamp(new GregorianCalendar(2999, Calendar.SEPTEMBER, 1).getTimeInMillis()));

        return reduction;
    }

    @BeforeAll
    static void resetDatabase() {
        try {
            ResetDatabase.reset("src/test/resources/resetProductTestDatabase.sql");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("========= FAILED RESET DATABASE ==========");
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void getByID() {
        Reduction reduction = reductionRepository.getByID(1);
        assertNotNull(reduction);
        assertEquals(0.5, reduction.getAmount());
        assertEquals("DEUXPOURUN", reduction.getActivationCode());

        reduction = reductionRepository.getByID(2);
        assertNotNull(reduction);
        assertEquals("UNPEUMOINS", reduction.getActivationCode());
        assertEquals(new Timestamp(new GregorianCalendar(2019, Calendar.SEPTEMBER, 5, 12, 0, 0).getTimeInMillis()), reduction.getBeginDateTime());

        reduction = reductionRepository.getByID(3);
        assertNotNull(reduction);
        assertEquals("CLAIREMENTMOINS", reduction.getActivationCode());
        assertEquals(new Timestamp(new GregorianCalendar(2019, Calendar.SEPTEMBER, 15, 12, 0, 0).getTimeInMillis()), reduction.getEndDateTime());
    }

    @Test
    @Order(2)
    void getProductReductions() {
        List<Reduction> reductions = reductionRepository.getProductReductions(1);
        assertNotNull(reductions);
        assertEquals(2, reductions.size());
    }

    @Test
    @Order(3)
    void getProductTypeReductions() {
        List<Reduction> reductions = reductionRepository.getProductTypeReductions(1);
        assertNotNull(reductions);
        assertEquals(1, reductions.size());
    }

    @Test
    @Order(4)
    void add() {
        Reduction reduction = prepareReduction();
        Reduction db_reduction = reductionRepository.add(reduction);
        assertNotNull(db_reduction);
        assertEquals(reduction.getAmount(), db_reduction.getAmount());
        assertEquals(reduction.getActivationCode(), db_reduction.getActivationCode());
    }

    @Test
    @Order(5)
    void save() {
        Reduction reduction = prepareReduction();
        reduction.setId(5);
        reduction.setAmount(0.7);
        reduction.setActivationCode("CHANGERACTIVATIONCODE");
        reductionRepository.save(reduction);
        Reduction db_reduction = reductionRepository.getByID(5);
        assertNotNull(db_reduction);
        assertEquals(reduction.getAmount(), db_reduction.getAmount());
    }

    @Test
    @Order(6)
    void delete() {
        reductionRepository.delete(5);
        Reduction reduction = reductionRepository.getByID(5);
        assertNull(reduction);
    }
}