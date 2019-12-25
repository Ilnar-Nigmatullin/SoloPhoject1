package com.soloProject1.demo.service;


import com.soloProject1.demo.dao.UserDAO;
import com.soloProject1.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> readUsers() {
        return userDAO.readUsers();
    }

    public void addUser(User user) {
        if (userDAO.findByUserName(user.getUserName()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.addUser(user);
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
