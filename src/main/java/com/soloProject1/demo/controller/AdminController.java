package com.soloProject1.demo.controller;

import com.soloProject1.demo.model.Role;
import com.soloProject1.demo.model.User;
import com.soloProject1.demo.service.RoleService;
import com.soloProject1.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService,
                           RoleService roleService,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    /*таблица всех юзеров*/
    @GetMapping("/allusers")
    public String adminPage(Model model, User user) {
        List<User> users = userService.readUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", user);

        return "admin";
    }

    /*метод добавление юзера*/
    @PostMapping("/addUser")
    public String createUser(User user,
                             @RequestParam(value = "role", defaultValue = "ROLE_USER") String[] roleArr) {
        Set<Role> roles = new HashSet<>();
        for (String s : roleArr) {
            Role roleUser = roleService.getRoleByName(s);
            roles.add(roleUser);
        }
        user.setRoles(roles);
        userService.addUser(user);

        return "redirect:/admin/allusers";
    }

    /*Метод изменения Юзера*/
    @PostMapping("/update/{id}")
    public String editUser(@PathVariable("id") int id, User user,
                           @RequestParam(value = "role", defaultValue = "ROLE_USER") String[] roleArr) {
        User user1 = userService.findUserById(id);
        if (user.getPassword().equals(user1.getPassword())) {
            passwordEncoder.upgradeEncoding(user.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        Set<Role> roles = new HashSet<>();
        for (String s : roleArr) {
            Role roleUser = roleService.getRoleByName(s);
            roles.add(roleUser);
        }
        user.setRoles(roles);
        userService.editUser(user);
        return "redirect:/admin/allusers";
    }

    /*Метод Удаления Юзера*/
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        User user = userService.findUserById(id);
        userService.deleteUser(user);
        return "redirect:/admin/allusers";
    }
}
