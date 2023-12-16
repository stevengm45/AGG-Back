package services;

import com.agg.certificados.entity.User;
import com.agg.certificados.entity.UserRol;

import java.util.Set;

public interface UserService {

    public User saveUser(User user, Set<UserRol> userRoles) throws Exception;
}
