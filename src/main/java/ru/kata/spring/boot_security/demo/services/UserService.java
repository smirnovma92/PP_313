package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = findByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", name));
        }

        return user;
    }

    public User findByUsername(String name) {
        return repository.findByUsername(name);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void addNewUser(User user) {
        repository.save(user);
    }

    public User getUser(long id) {
        return repository.findById(id).orElse(null);
    }

    public void updateUser(User user) {
        repository.saveAndFlush(user);
    }

    public void userDelete(long id) {
        repository.deleteById(id);
    }
}
