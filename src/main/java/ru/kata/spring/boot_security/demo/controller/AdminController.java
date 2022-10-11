package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Controller
public class AdminController {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String printUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        model.addAttribute("user", new User());
        return "admin";
    }

    @GetMapping("/admin/create")
    public String register(Model model) {
        model.addAttribute("allRoles", roleService.getAll());
        model.addAttribute("user", new User());
        return "/admin/create";
    }

    @PostMapping("/admin/create")
    public String inputUser(@ModelAttribute("user") User user) {
        userService.addNewUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/admin/edit";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.userDelete(id);
        return "redirect:/admin";
    }
}
