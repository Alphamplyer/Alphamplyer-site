package com.alphamplyer.microservice.products.repositories.dao.impl;

import com.alphamplyer.microservice.products.ResetDatabase;
import com.alphamplyer.microservice.products.models.ProductType;
import com.alphamplyer.microservice.products.repositories.dao.interf.IProductTypeRepository;
import org.junit.jupiter.api.*;
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
class ProductTypeRepositoryTest {

    @Autowired
    private IProductTypeRepository productTypeRepository;

    private ProductType prepareProductType() {
        ProductType productType = new ProductType();

        productType.setParentId(1);
        productType.setCode("TEST2");
        productType.setName("Test 2");
        productType.setDescription("Test description");

        return productType;
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
        ProductType productType = productTypeRepository.getByID(1);
        assertNotNull(productType);
        assertEquals("TEST", productType.getCode());

        productType = productTypeRepository.getByID(2);
        assertNotNull(productType);
        assertEquals("GAME", productType.getCode());

        productType = productTypeRepository.getByID(3);
        assertNotNull(productType);
        assertEquals("GAME-TEST", productType.getCode());

        productType = productTypeRepository.getByID(4);
        assertNotNull(productType);
        assertEquals("GAME-SUB", productType.getCode());
    }

    @Test
    @Order(2)
    void getParent() {
        ProductType productType = productTypeRepository.getParent(3);
        assertNotNull(productType);
        assertEquals(1, productType.getId());
        assertEquals("TEST", productType.getCode());

        productType = productTypeRepository.getParent(4);
        assertNotNull(productType);
        assertEquals(2, productType.getId());
        assertEquals("GAME", productType.getCode());
    }

    @Test
    @Order(3)
    void getChilds() {
        List<ProductType> productTypes = productTypeRepository.getChilds(1);
        assertNotNull(productTypes);
        assertEquals(3, productTypes.get(0).getId());
        assertEquals("GAME-TEST", productTypes.get(0).getCode());

        productTypes = productTypeRepository.getChilds(2);
        assertNotNull(productTypes);
        assertEquals(4, productTypes.get(0).getId());
        assertEquals("GAME-SUB", productTypes.get(0).getCode());
    }

    @Test
    @Order(4)
    void getMainProductType() {
        List<ProductType> productTypes = productTypeRepository.getMainProductType();
        assertNotNull(productTypes);
        assertEquals(2, productTypes.size());
        assertEquals("TEST", productTypes.get(0).getCode());
        assertEquals("GAME", productTypes.get(1).getCode());
    }

    @Test
    @Order(5)
    void add() {
        ProductType productType = prepareProductType();
        ProductType db_productType = productTypeRepository.add(productType);
        assertNotNull(db_productType);

        assertNotNull(db_productType.getId());
        assertEquals(productType.getParentId(), db_productType.getParentId());
        assertEquals(productType.getCode(), db_productType.getCode());
        assertEquals(productType.getName(), db_productType.getName());
        assertEquals(productType.getDescription(), db_productType.getDescription());
    }

    @Test
    @Order(6)
    void save() {
        ProductType productType = prepareProductType();

        productType.setId(5);
        productType.setParentId(2);
        productType.setCode("CODEMODIFIED");
        productType.setName("Test name modified");
        productType.setDescription("Test description modified");

        productTypeRepository.save(productType);
        ProductType db_productType = productTypeRepository.getByID(5);

            assertNotNull(db_productType);

        assertNotNull(db_productType.getId());
        assertEquals(productType.getParentId(), db_productType.getParentId());
        assertEquals(productType.getCode(), db_productType.getCode());
        assertEquals(productType.getName(), db_productType.getName());
        assertEquals(productType.getDescription(), db_productType.getDescription());
    }

    @Test
    @Order(7)
    void delete() {
        productTypeRepository.delete(5);
        ProductType productType = productTypeRepository.getByID(5);
        assertNull(productType);
    }
}