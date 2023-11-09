package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class testUsers {
    private final RoleService roleService;
    private final UserService userService;

    final Role role_admin = new Role("ROLE_ADMIN");
    final Role role_user = new Role("ROLE_USER");

    @Autowired
    public testUsers(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    public Set<Role> roleAdmin() {
        Set<Role> roles = new HashSet<>();
        roles.add(role_admin);
        return roles;
    }

    public Set<Role> roleUser() {
        Set<Role> roles = new HashSet<>();
        roles.add(role_user);
        return roles;
    }

    @Transactional
    @Bean
    public void init() {
        roleService.save(role_admin);
        roleService.save(role_user);
        userService.saveUser(new User("admin", 22, "admin", roleAdmin()));
        userService.saveUser(new User("user", 35, "user", roleUser()));
    }
}
