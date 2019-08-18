package com.alphamplyer.microservice.users.repositories.dao.interf;

import com.alphamplyer.microservice.users.models.User;

import java.util.List;

public interface IUserRepository {

    User getById(Integer id);
    User getByUsername(String username);
    User getByEmail(String email);

    Integer countUserWithSameUsername(String username);
    Integer countUserWithSameEmail(String email);

    List<User> getAll(Integer offset, Integer limit);
    List<User> getAllByRoleID(Integer roleId, Integer offset, Integer limit);

    User insert(User user);
    void update(User user);
    void delete(Integer id);
}
