package com.soloProject1.demo.controller;

import com.soloProject1.demo.model.Role;
import com.soloProject1.demo.model.User;
import com.soloProject1.demo.service.RoleService;
import com.soloProject1.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/admin/rest")
public class AdminRestController {
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminRestController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
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

    @PutMapping("/update/{id}")
    public void updateUser(
            @PathVariable(value = "id") int id, @Valid @RequestBody User userDetails) {
        User user = userService.findUserById(id);
        if (user.getPassword().equals(userDetails.getPassword())) {
            passwordEncoder.upgradeEncoding(userDetails.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        user.setUserName(userDetails.getUserName());
        user.setRoles(userDetails.getRoles());
        userService.editUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(value = "id") int id) {
        User user = userService.findUserById(id);
        userService.deleteUser(user);
    }

    @GetMapping("/find/userByName")
    public User findByUserName(@PathVariable String username) {
        return userService.findByUserName(username);
    }
}
