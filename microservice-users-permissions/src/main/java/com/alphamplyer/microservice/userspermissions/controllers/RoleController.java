package com.alphamplyer.microservice.userspermissions.controllers;

import com.alphamplyer.microservice.userspermissions.exceptions.NotFoundException;
import com.alphamplyer.microservice.userspermissions.models.Role;
import com.alphamplyer.microservice.userspermissions.repositories.dao.interf.IRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private IRoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping(value = "/roles/{id}")
    public ResponseEntity<Role> getRole(@PathVariable(name = "id") Integer id) {
        Role role;

        try {
            role = roleRepository.getById(id);
        } catch (DataAccessException e) {
            logger.error("User not found in database", e);
            throw new NotFoundException(String.format("User not found (ID = %d)", id));
        }

        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
