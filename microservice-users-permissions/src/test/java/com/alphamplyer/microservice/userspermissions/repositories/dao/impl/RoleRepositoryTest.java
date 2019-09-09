package com.alphamplyer.microservice.userspermissions.repositories.dao.impl;

import com.alphamplyer.microservice.userspermissions.ResetDatabase;
import com.alphamplyer.microservice.userspermissions.models.Role;
import com.alphamplyer.microservice.userspermissions.repositories.dao.interf.IRoleRepository;
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
class RoleRepositoryTest {

    @Autowired
    private IRoleRepository roleRepository;

    private Role prepareRole() {
        Role role = new Role();

        role.setName("Test");

        return role;
    }

    @BeforeAll
    static void resetDatabase() {
        try {
            ResetDatabase.reset("src/test/resources/resetUserPermissionsTestDatabase.sql");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("========= FAILED RESET DATABASE ==========");
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void getById() {
        Role role = roleRepository.getById(3);
        assertNotNull(role);
        assertEquals("Subscriber", role.getName());

        role = roleRepository.getById(4);
        assertNotNull(role);
        assertEquals("Administrator", role.getName());

        role = roleRepository.getById(1);
        assertNotNull(role);
        assertEquals("Super Administrator", role.getName());
    }

    @Test
    @Order(2)
    void getByName() {
        Role role = roleRepository.getByName("Subscriber");
        assertNotNull(role);
        assertEquals(3, role.getId());

        role = roleRepository.getByName("Member");
        assertNotNull(role);
        assertEquals(2, role.getId());
    }

    @Test
    @Order(3)
    void getRoles() {
        List<Role> roles = roleRepository.getRoles();
        assertNotNull(roles);
        assertEquals(5, roles.size());
        assertEquals("Subscriber", roles.get(2).getName());
        assertEquals("Moderator", roles.get(4).getName());
    }

    @Test
    @Order(4)
    void add() {
        Role role = prepareRole();
        Role db_role = roleRepository.add(role);
        assertNotNull(db_role);
        assertNotNull(db_role.getId());
        assertNotNull(db_role.getIndex());
        assertEquals(role.getName(), db_role.getName());
    }

    @Test
    @Order(5)
    void save() {
        Role role = prepareRole();
        role.setId(6);
        role.setIndex(4);
        role.setName("MODIFIED");

        roleRepository.save(role);
        Role db_role = roleRepository.getById(6);
        assertNotNull(db_role);
        assertEquals(role.getName(), db_role.getName());
    }
}