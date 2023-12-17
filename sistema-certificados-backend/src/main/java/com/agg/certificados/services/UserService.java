package services;

import com.agg.certificados.entity.User;
import com.agg.certificados.entity.UserRol;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {

    public User saveUser(User user, Set<UserRol> userRoles) throws Exception;
}
