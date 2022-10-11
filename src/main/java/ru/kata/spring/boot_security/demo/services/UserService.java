package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    public User findByUsername(String name);
    public List<User> getAllUsers();

    public void addNewUser(User user);
    public User getUser(long id);
    public void updateUser(User user);
    public void userDelete(long id);
}
