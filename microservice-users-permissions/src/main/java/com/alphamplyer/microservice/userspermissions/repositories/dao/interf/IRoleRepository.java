package com.alphamplyer.microservice.userspermissions.repositories.dao.interf;

import com.alphamplyer.microservice.userspermissions.models.Role;

import java.util.List;

public interface IRoleRepository {

    /**
     * Get role by ID
     * @param id role ID
     * @return role or null
     */
    Role getById(Integer id);

    /**
     * Get role by name
     * @param name role name
     * @return role or null
     */
    Role getByName(String name);

    /**
     * Get all roles
     * @return list of role or null
     */
    List<Role> getRoles();

    /**
     * Add role into database
     * @param role role to add
     * @return added role or null
     */
    Role add(Role role);

    /**
     * Update role
     * @param role role to update
     */
    void save(Role role);

    /**
     * Delete role by id
     * @param id role ID
     */
    void remove(Integer id);
}
