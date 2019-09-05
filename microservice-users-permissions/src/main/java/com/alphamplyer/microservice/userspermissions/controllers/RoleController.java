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

import java.util.List;


@RestController
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private IRoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Get role from its ID
     * @param id role ID
     * @return role or a NotFoundException error
     */
    @GetMapping(value = "/roles/{id}")
    public ResponseEntity<Role> getRole(@PathVariable(name = "id") Integer id) {
        Role role;

        try {
            role = roleRepository.getById(id);
        } catch (DataAccessException e) {
            logger.error("Role not found in database", e);
            throw new NotFoundException(String.format("Role not found (ID = %d)", id));
        }

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    /**
     * Get all roles
     * @return roles list or a NotFoundException error
     */
    @GetMapping(value = "/roles")
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles;

        try {
            roles = roleRepository.getRoles();
        } catch (DataAccessException e) {
            logger.error("Roles not found in database", e);
            throw new NotFoundException("Roles not found");
        }

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
