package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.Collection;

public interface RoleService {
    public Collection<Role> getAll();
}
