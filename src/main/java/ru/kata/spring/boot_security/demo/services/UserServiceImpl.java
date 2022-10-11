package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {
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
    @Override
    public User findByUsername(String name) {
        return repository.findByUsername(name);
    }
    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }
    @Override
    public void addNewUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }
    @Override
    public User getUser(long id) {
        return repository.findById(id).orElse(null);
    }
    @Override
    public void updateUser(User user) {
        repository.saveAndFlush(user);
    }
    @Override
    public void userDelete(long id) {
        repository.deleteById(id);
    }

}
