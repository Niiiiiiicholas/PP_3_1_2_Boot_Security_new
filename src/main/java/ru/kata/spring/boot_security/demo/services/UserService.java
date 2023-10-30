package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> showUsers();

    User showById(long id);

    void saveUser(User user);

    void update(User user, Long id);

    void delete(Long id);

}
