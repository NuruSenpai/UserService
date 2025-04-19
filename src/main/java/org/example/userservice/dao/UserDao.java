package org.example.userservice.dao;

import org.example.userservice.entity.User;

import java.util.List;


public interface UserDao {
    void save(User user);
    User findById(long id);
    List<User> findAll();
    void update(User user);
    void delete(long id);
}
