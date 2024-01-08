package com.agg.certificados.controllers;

import com.agg.certificados.entity.Rol;
import com.agg.certificados.entity.User;
import com.agg.certificados.entity.UserRol;
import com.agg.certificados.services.usersServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) throws Exception{

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRol> userRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(1L);
        rol.setRolName("ADMIN");

        UserRol userRol = new UserRol();
        userRol.setUser(user);
        userRol.setRol(rol);

        userRoles.add(userRol);
        return userService.saveUser(user, userRoles);
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
