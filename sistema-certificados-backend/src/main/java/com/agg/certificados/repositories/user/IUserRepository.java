package com.agg.certificados.repositories.user;

import com.agg.certificados.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
