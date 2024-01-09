package com.agg.certificados.repositories.manager;

import com.agg.certificados.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IManagerRepository extends JpaRepository<Manager,Long> {
}
