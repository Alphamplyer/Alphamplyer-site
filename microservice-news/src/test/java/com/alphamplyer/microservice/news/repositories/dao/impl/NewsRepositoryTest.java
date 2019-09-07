package com.alphamplyer.microservice.news.repositories.dao.impl;



import com.alphamplyer.microservice.news.ResetDatabase;
import com.alphamplyer.microservice.news.models.News;
import com.alphamplyer.microservice.news.repositories.dao.interf.INewsRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NewsRepositoryTest {

    @Autowired
    private INewsRepository newsRepository;

    private News setupNews() {
        News news = new News();
        news.setCategoryId(1);
        news.setMainImageId(1);
        news.setTitle("News title Test");
        news.setContent("News content Test");
        news.setDescription("News description Test");

        List<Integer> authorsIDs = new ArrayList<>();
        authorsIDs.add(1);

        news.setAuthors(authorsIDs);

        Calendar calendar = new GregorianCalendar();
        calendar.set(2019, 8, 1, 12, 0, 0);

        news.setPublicationTime(new Timestamp(calendar.getTimeInMillis()));

        return news;
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
        News news = newsRepository.getById(3, false);
        assertNotNull(news);
        assertEquals("Website Update", news.getTitle());

        news = newsRepository.getById(1, false);
        assertNotNull(news);
        assertEquals("Version 1", news.getTitle());

        news = newsRepository.getById(-1, false);
        assertNull(news);
    }

    @Test
    @Order(2)
    void getNews() {
        List<News> news = newsRepository.getNews(0, 0, false);
        assertEquals(6, news.size());

        news = newsRepository.getNews(0, 4, false);
        assertEquals(4, news.size());

        news = newsRepository.getNews(1, 0, false);
        assertEquals(5, news.size());

        news = newsRepository.getNews(-10, 1, false);
        assertEquals(1, news.size());

        news = newsRepository.getNews(9999, 1, false);
        assertNull(news);
    }

    @Test
    @Order(3)
    void getNewsAuthor() {
        List<Integer> authorsIDs = newsRepository.getNewsAuthor(1);
        assertEquals(3, authorsIDs.size());

        authorsIDs = newsRepository.getNewsAuthor(2);
        assertEquals(2, authorsIDs.size());

        authorsIDs = newsRepository.getNewsAuthor(3);
        assertEquals(3, authorsIDs.size());

        authorsIDs = newsRepository.getNewsAuthor(4);
        assertEquals(2, authorsIDs.size());

        authorsIDs = newsRepository.getNewsAuthor(5);
        assertEquals(1, authorsIDs.size());

        authorsIDs = newsRepository.getNewsAuthor(6);
        assertEquals(3, authorsIDs.size());

        // return null when no result
        authorsIDs = newsRepository.getNewsAuthor(-1);
        assertNull(authorsIDs);
    }

    @Test
    @Order(4)
    void getNewsByCategoryId() {
        List<News> news = newsRepository.getNewsByCategoryId(1, 0, 0, false);
        assertEquals(4, news.size()); // get all news in category and sub category (1 in c1, 1 in c3 in c1, 1 in c4 in c1 and 1 in c6 in c3 in c1)

        news = newsRepository.getNewsByCategoryId(2, 0, 0, false);
        assertEquals(2, news.size());

        news = newsRepository.getNewsByCategoryId(3, 0, 0, false);
        assertEquals(2, news.size());

        news = newsRepository.getNewsByCategoryId(4, 0, 0, false);
        assertEquals(1, news.size());

        news = newsRepository.getNewsByCategoryId(5, 0, 0, false);
        assertEquals(1, news.size());

        news = newsRepository.getNewsByCategoryId(6, 0, 0, false);
        assertEquals(1, news.size());

        // return null when no result
        news = newsRepository.getNewsByCategoryId(6, 9999, 1, false);
        assertNull(news);
    }

    @Test
    @Order(5)
    void getNewsByAuthorId() {
        List<News> news = newsRepository.getNewsByAuthorId(1, 0, 0, false);
        assertEquals(6, news.size());

        news = newsRepository.getNewsByAuthorId(2, 0, 0, false);
        assertEquals(4, news.size());

        news = newsRepository.getNewsByAuthorId(3, 0, 0, false);
        assertEquals(2, news.size());

        news = newsRepository.getNewsByAuthorId(4, 0, 0, false);
        assertEquals(2, news.size());

        news = newsRepository.getNewsByAuthorId(1, 9999, 1, false);
        assertNull(news);
    }

    @Test
    @Order(6)
    void save() {
        News news = setupNews();
        News db_news = newsRepository.save(news);

        assertNotNull(db_news);
        assertEquals(news.getCategoryId(), db_news.getCategoryId());
        assertEquals(news.getMainImageId(), db_news.getMainImageId());
        assertEquals(news.getTitle(), db_news.getTitle());
        assertEquals(news.getContent(), db_news.getContent());
        assertEquals(news.getDescription(), db_news.getDescription());
        assertEquals(news.getAuthors().size(), db_news.getAuthors().size());
        assertEquals(news.getPublicationTime().getTime(), db_news.getPublicationTime().getTime());
    }

    @Test
    @Order(7)
    void update() {
        News news = setupNews();

        news.setCategoryId(2);
        news.setMainImageId(2);
        news.setTitle("Test title update");
        news.setContent("Test content update");
        news.setDescription("Test description update");
        news.setId(7);

        newsRepository.update(news);

        News db_news = newsRepository.getById(7, false);

        assertNotNull(db_news);
        assertEquals(news.getCategoryId(), db_news.getCategoryId());
        assertEquals(news.getMainImageId(), db_news.getMainImageId());
        assertEquals(news.getTitle(), db_news.getTitle());
        assertEquals(news.getContent(), db_news.getContent());
        assertEquals(news.getDescription(), db_news.getDescription());
    }

    @Test
    @Order(8)
    void delete() {
        newsRepository.delete(7);
        News news = newsRepository.getById(7, false);
        assertNull(news);
    }

    @Test
    @Order(9)
    void deleteNewsOfCategory() {
        newsRepository.deleteNewsOfCategory(6);
        News news = newsRepository.getById(6, false);
        assertNull(news);
    }
}