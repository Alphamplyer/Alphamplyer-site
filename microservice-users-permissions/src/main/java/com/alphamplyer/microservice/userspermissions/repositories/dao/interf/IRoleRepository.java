package com.alphamplyer.microservice.userspermissions.repositories.dao.interf;

import com.alphamplyer.microservice.userspermissions.models.Role;

import java.util.List;

public interface IRoleRepository {

    Role getById(Integer id);
    Role getByName(String name);

    List<Role> getRoles();

    Role add(Role role);
    void save(Role role);
    void remove(Integer id);
}
