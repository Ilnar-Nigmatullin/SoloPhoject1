package com.soloProject1.demo.controller;

import com.soloProject1.demo.model.Role;
import com.soloProject1.demo.model.User;
import com.soloProject1.demo.service.RoleService;
import com.soloProject1.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/admin/rest")
public class AdminRestController {
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.readUsers();
    }

    @PostMapping("/create")
    public void createUser(@Valid @RequestBody User user) {
        Set<Role> roles = new HashSet<>();
        Role roleUser = roleService.getRoleByName("ROLE_ADMIN");
        roles.add(roleUser);
        user.setRoles(roles);

        userService.addUser(user);
    }

    @PutMapping("/update/{id}")
    public void updateUser(
            @PathVariable(value = "id") int id, @Valid @RequestBody User userDetails) {
        User user = userService.findUserById(id);
        user.setUserName(userDetails.getUserName());
        user.setPassword(userDetails.getPassword());
        user.setRoles(userDetails.getRoles());
        userService.editUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(value = "id") int id) {
        User user = userService.findUserById(id);
        userService.deleteUser(user);
    }
}
