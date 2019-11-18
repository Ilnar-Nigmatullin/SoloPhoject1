package com.soloProject1.demo.dao;

import com.soloProject1.demo.model.User;

import java.util.List;

public interface UserDAO {
    List<User> readUsers();

    void addUser(User user);

    void editUser(User user);

    void deleteUser(User user);

    User findByUserName(String userName);

    User findUserById(int id);
}
