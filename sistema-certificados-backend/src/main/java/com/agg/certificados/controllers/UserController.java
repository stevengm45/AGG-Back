package com.agg.certificados.controllers;

import com.agg.certificados.entity.Rol;
import com.agg.certificados.entity.User;
import com.agg.certificados.entity.UserRol;
import com.agg.certificados.services.usersServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) throws Exception{
        Set<UserRol> roles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setName("Normal");

        UserRol userRol = new UserRol();
        userRol.setUser(user);
        userRol.setRol(rol);

        return userService.saveUser(user, roles);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }
}
