package com.agg.certificados.services;

import com.agg.certificados.entity.User;
import com.agg.certificados.entity.UserRol;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface UserService {

    public User saveUser(User user, Set<UserRol> userRoles) throws Exception;

    public User getUser(String username);

    public void deleteUser(Long userId);
}
