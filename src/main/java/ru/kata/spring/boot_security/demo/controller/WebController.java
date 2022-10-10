package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class WebController {
    private final UserService userService;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String printUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "admin";
    }

    @GetMapping("/admin/create")
    public String register(Model model) {
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
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "/admin/edit";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Long id) {

        userService.userDelete(id);
        return "redirect:/admin";
    }


    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("userok", user);
        return "user";
    }


}
