package com.soloProject1.demo.controller;

import com.soloProject1.demo.model.User;
import com.soloProject1.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/rest")
public class AdminRestController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminRestController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.readUsers();
    }

    @PostMapping("/create")
    public void createUser(@Valid @RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user) {
        userService.editUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(value = "id") int id) {
        User user = userService.findUserById(id);
        userService.deleteUser(user);
    }

    @GetMapping("/find/byUserName/{username}")
    public User findByUserName(@PathVariable String username) {
        return userService.findByUserName(username);
    }
}
