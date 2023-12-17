package com.agg.certificados.services.impl;

import com.agg.certificados.entity.User;
import com.agg.certificados.entity.UserRol;
import com.agg.certificados.repository.RolRepository;
import com.agg.certificados.repository.UserRepository;
import com.agg.certificados.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public User saveUser(User user, Set<UserRol> userRoles) throws Exception {
        User userLocal = userRepository.findByUsername(user.getUsername());

        if(userLocal != null) {
            System.out.println("El usuario ya existe");
            throw new Exception("E usuario ya esta presente");
        } else {
            for (UserRol userRol:userRoles) {
                rolRepository.save(userRol.getRol());
            }
            user.getUserRoles().addAll(userRoles);
            userLocal = userRepository.save(user);
        }
        return userLocal;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
