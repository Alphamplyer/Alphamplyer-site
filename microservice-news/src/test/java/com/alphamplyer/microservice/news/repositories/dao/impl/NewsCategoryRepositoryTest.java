package com.alphamplyer.microservice.news.repositories.dao.impl;

import com.alphamplyer.microservice.news.ResetDatabase;
import com.alphamplyer.microservice.news.models.NewsCategory;
import com.alphamplyer.microservice.news.repositories.dao.interf.INewsCategoryRepository;
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
class NewsCategoryRepositoryTest {

    @Autowired
    private INewsCategoryRepository newsCategoryRepository;

    private NewsCategory prepareNewsCategory() {
        NewsCategory newsCategory = new NewsCategory();

        newsCategory.setCreatorId(1);
        newsCategory.setParentId(1);
        newsCategory.setName("Test");
        newsCategory.setDescription("Test description");

        return newsCategory;
    }

    @BeforeAll
    static void resetDatabase() {
        try {
            ResetDatabase.reset("src/test/resources/resetNewsTestDatabase.sql");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("========= FAILED RESET DATABASE ==========");
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void getById() {
        NewsCategory newsCategory = newsCategoryRepository.getById(1);
        assertNotNull(newsCategory);
        assertEquals("Info", newsCategory.getName());

        newsCategory = newsCategoryRepository.getById(2);
        assertNotNull(newsCategory);
        assertEquals("MAJ", newsCategory.getName());

        newsCategory = newsCategoryRepository.getById(3);
        assertNotNull(newsCategory);
        assertEquals("Shop", newsCategory.getName());

        newsCategory = newsCategoryRepository.getById(4);
        assertNotNull(newsCategory);
        assertEquals("Game", newsCategory.getName());

        newsCategory = newsCategoryRepository.getById(5);
        assertNotNull(newsCategory);
        assertEquals("Game Update", newsCategory.getName());

        newsCategory = newsCategoryRepository.getById(6);
        assertNotNull(newsCategory);
        assertEquals("New Product", newsCategory.getName());

        newsCategory = newsCategoryRepository.getById(-1);
        assertNull(newsCategory);
    }

    @Test
    @Order(2)
    void getParent() {
        NewsCategory parentCategory = newsCategoryRepository.getParent(3);
        assertNotNull(parentCategory);
        assertEquals("Info", parentCategory.getName());

        parentCategory = newsCategoryRepository.getParent(1);
        assertNull(parentCategory);
    }

    @Test
    @Order(3)
    void getChild() {
        List<NewsCategory> newsCategories = newsCategoryRepository.getChild(1);
        assertNotNull(newsCategories);
        assertEquals(2, newsCategories.size());
        assertEquals("Shop", newsCategories.get(0).getName());
        assertEquals("Game", newsCategories.get(1).getName());

        newsCategories = newsCategoryRepository.getChild(2);
        assertNotNull(newsCategories);
        assertEquals(1, newsCategories.size());
        assertEquals("Game Update", newsCategories.get(0).getName());

        newsCategories = newsCategoryRepository.getChild(6);
        assertNull(newsCategories);
    }

    @Test
    @Order(4)
    void getMainCategories() {
        List<NewsCategory> newsCategories = newsCategoryRepository.getMainCategories();
        assertNotNull(newsCategories);
        assertEquals(2, newsCategories.size());
        assertEquals("Info", newsCategories.get(0).getName());
        assertEquals("MAJ", newsCategories.get(1).getName());
    }

    @Test
    @Order(5)
    void save() {
        NewsCategory newsCategory = prepareNewsCategory();
        NewsCategory db_newsCategory = newsCategoryRepository.save(newsCategory);
        assertNotNull(db_newsCategory);

        assertEquals(newsCategory.getCreatorId(), db_newsCategory.getCreatorId());
        assertEquals(newsCategory.getParentId(), db_newsCategory.getParentId());
        assertEquals(newsCategory.getName(), db_newsCategory.getName());
        assertEquals(newsCategory.getDescription(), db_newsCategory.getDescription());
        assertNotNull(db_newsCategory.getCreatedAt());
        assertNotNull(db_newsCategory.getUpdatedAt());
    }

    @Test
    @Order(6)
    void update() {
        NewsCategory newsCategory = prepareNewsCategory();

        newsCategory.setId(7);
        newsCategory.setParentId(2);
        newsCategory.setName("Test name update");
        newsCategory.setDescription("Test description update");

        /*
        for (int i = 0; i < 10; i++) {
            NewsCategory nc = newsCategoryRepository.getById(i);
            if (nc != null)
                System.out.println(nc.getId() + " : " + nc.getName());
        }
         */

        newsCategoryRepository.update(newsCategory);
        NewsCategory db_newsCategory = newsCategoryRepository.getById(7);
        assertNotNull(db_newsCategory);

        assertEquals(newsCategory.getParentId(), db_newsCategory.getParentId());
        assertEquals(newsCategory.getName(), db_newsCategory.getName());
        assertEquals(newsCategory.getDescription(), db_newsCategory.getDescription());
    }

    @Test
    @Order(7)
    void delete() {
        newsCategoryRepository.delete(7);

        NewsCategory newsCategory = newsCategoryRepository.getById(7);
        assertNull(newsCategory);
    }
}