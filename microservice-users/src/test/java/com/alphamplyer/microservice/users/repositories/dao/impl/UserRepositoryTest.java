package com.alphamplyer.microservice.users.repositories.dao.impl;

import com.alphamplyer.microservice.users.ResetDatabase;
import com.alphamplyer.microservice.users.models.User;
import com.alphamplyer.microservice.users.repositories.dao.interf.IUserRepository;
import com.alphamplyer.microservice.users.utlis.Password;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

    @Autowired
    IUserRepository userRepository;

    private static int makedUser = 0;

    private User prepareUser(){
        User user = new User();

        user.setRoleId(0);
        user.setUsername("Test User " + makedUser);
        user.setEmail("test_user_" + makedUser + "@iyard.fr");
        user.setSalt(Password.generateSalt(128));
        user.setPassword(Password.generateSecurePassword("random", user.getSalt()));
        user.setTermAccepted(false);
        user.setTermAccepted(false);
        user.setBirthDate(new GregorianCalendar(2019, 9, 9).getTime());
        makedUser++;

        return user;
    }

    @BeforeAll
    static void resetDatabase() {
        try {
            ResetDatabase.reset("src/test/resources/resetUsersTestDatabase.sql");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("========= FAILED RESET DATABASE ==========");
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void insert() {
        for (int i = 0; i < 10; i++) {
            User user = prepareUser();

            User insertedUser = userRepository.insert(user);
            assertNotNull(insertedUser);
            assertNotNull(insertedUser.getId());
            assertEquals(user.getRoleId(), insertedUser.getRoleId());
            assertEquals(user.getPassword(), insertedUser.getPassword());
            assertEquals(user.getEmail(), insertedUser.getEmail());
            assertEquals(user.getUsername(), insertedUser.getUsername());
            assertEquals(user.getSalt(), insertedUser.getSalt());
            assertEquals(user.getEmailValidated(), insertedUser.getEmailValidated());
            assertEquals(user.getTermAccepted(), insertedUser.getTermAccepted());
            assertEquals(user.getBirthDate(), insertedUser.getBirthDate());
            assertNotNull(insertedUser.getCreatedAt());
            assertNotNull(insertedUser.getUpdatedAt());
        }
    }

    @Test
    @Order(2)
    void getById() {
        User user = userRepository.getById(1);
        assertNotNull(user);
        assertEquals("Test User 0", user.getUsername());

        user = userRepository.getById(2);
        assertNotNull(user);
        assertEquals("Test User 1", user.getUsername());

        user = userRepository.getById(3);
        assertNotNull(user);
        assertEquals("Test User 2", user.getUsername());
    }

    @Test
    @Order(3)
    void getByUsername() {
        User user = userRepository.getByUsername("Test User 0");
        assertNotNull(user);
        assertEquals(1, user.getId());

        user = userRepository.getByUsername("Test User 1");
        assertNotNull(user);
        assertEquals(2, user.getId());

        user = userRepository.getByUsername("Test User 2");
        assertNotNull(user);
        assertEquals(3, user.getId());
    }

    @Test
    @Order(4)
    void getByEmail() {
        User user = userRepository.getByEmail("test_user_0@iyard.fr");
        assertNotNull(user);
        assertEquals(1, user.getId());

        user = userRepository.getByEmail("test_user_1@iyard.fr");
        assertNotNull(user);
        assertEquals(2, user.getId());

        user = userRepository.getByEmail("test_user_2@iyard.fr");
        assertNotNull(user);
        assertEquals(3, user.getId());
    }

    @Test
    @Order(5)
    void countUserWithSameUsername() {
        Integer count = userRepository.countUserWithSameUsername("Test User 0");
        assertNotNull(count);
        assertEquals(1, count);

        count = userRepository.countUserWithSameUsername("Test User 1");
        assertNotNull(count);
        assertEquals(1, count);

        count = userRepository.countUserWithSameUsername("Test User 2");
        assertNotNull(count);
        assertEquals(1, count);

        count = userRepository.countUserWithSameUsername("Test User 3");
        assertNotNull(count);
        assertEquals(1, count);
    }

    @Test
    @Order(6)
    void countUserWithSameEmail() {
        Integer count = userRepository.countUserWithSameEmail("test_user_0@iyard.fr");
        assertNotNull(count);
        assertEquals(1, count);

        count = userRepository.countUserWithSameEmail("test_user_1@iyard.fr");
        assertNotNull(count);
        assertEquals(1, count);

        count = userRepository.countUserWithSameEmail("test_user_2@iyard.fr");
        assertNotNull(count);
        assertEquals(1, count);

        count = userRepository.countUserWithSameEmail("test_user_3@iyard.fr");
        assertNotNull(count);
        assertEquals(1, count);
    }

    @Test
    @Order(7)
    void getAll() {
        List<User> users = userRepository.getAll(0, 0);
        assertNotNull(users);
        assertEquals(10, users.size());

        users = userRepository.getAll(2, 0);
        assertNotNull(users);
        assertEquals(8, users.size());

        users = userRepository.getAll(0, 5);
        assertNotNull(users);
        assertEquals(5, users.size());

        users = userRepository.getAll(4, 5);
        assertNotNull(users);
        assertEquals(5, users.size());

        users = userRepository.getAll(8, 3);
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    @Order(8)
    void update() {
        User user = userRepository.getById(1);
        User user2 = userRepository.getById(2);

        user.setRoleId(1);
        user2.setRoleId(1);
        user.setUsername("Test User 0 Modified");
        user2.setUsername("Test User 1 Modified");
        user.setEmail("test_user_0_modifier@iyard.fr");
        user2.setEmail("test_user_1_modifier@iyard.fr");

        userRepository.update(user);
        userRepository.update(user2);
        User db_user = userRepository.getById(1);
        User db_user2 = userRepository.getById(2);

        assertNotNull(db_user);
        assertNotNull(db_user2);

        assertEquals(user.getRoleId(), db_user.getRoleId());
        assertEquals(user2.getRoleId(), db_user2.getRoleId());

        assertEquals(user.getUsername(), db_user.getUsername());
        assertEquals(user2.getUsername(), db_user2.getUsername());

        assertEquals(user.getEmail(), db_user.getEmail());
        assertEquals(user2.getEmail(), db_user2.getEmail());
    }


    @Test
    @Order(9)
    void getAllByRoleID() {
        List<User> users = userRepository.getAllByRoleID(1, 0, 0);
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    @Order(10)
    void getAllByListIDs() {
        List<User> users = userRepository.getAllByListIDs(Arrays.asList(3, 5, 6));
        assertNotNull(users);
        assertEquals(3, users.size());
        assertEquals("Test User 2", users.get(0).getUsername());
        assertEquals("Test User 4", users.get(1).getUsername());
        assertEquals("Test User 5", users.get(2).getUsername());
    }


    @Test
    @Order(11)
    void delete() {
        userRepository.delete(10);
        User user = userRepository.getById(10);
        assertNull(user);
    }
}