package com.agg.certificados.repositories.userRepository;

import com.agg.certificados.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
