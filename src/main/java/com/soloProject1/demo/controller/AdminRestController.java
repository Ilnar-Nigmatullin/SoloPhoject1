package com.soloProject1.demo.controller;

import com.soloProject1.demo.model.User;
import com.soloProject1.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/rest")
public class AdminRestController {
    private UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.readUsers();
    }

    @PostMapping("/users")
    public void createUser(@Valid @RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(
            @PathVariable(value = "id") int id, @Valid @RequestBody User userDetails) {
        User user = userService.findUserById(id);
        user.setUserName(userDetails.getUserName());
        user.setPassword(userDetails.getPassword());
        user.setRoles(userDetails.getRoles());
        userService.editUser(user);
    }

    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int id) {
        User user = userService.findUserById(id);

        userService.deleteUser(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
