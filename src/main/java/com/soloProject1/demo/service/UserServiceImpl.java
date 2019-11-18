package com.soloProject1.demo.service;


import com.soloProject1.demo.dao.UserDAO;
import com.soloProject1.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> readUsers() {
        return userDAO.readUsers();
    }

    public void addUser(User user) {
        if (userDAO.findByUserName(user.getUserName()) == null) {
            userDAO.addUser(user);
        } else {
        }
    }

    public void editUser(User user) {
        userDAO.editUser(user);
    }

    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }

    @Override
    public User findUserById(int id) {
        return userDAO.findUserById(id);
    }
}
