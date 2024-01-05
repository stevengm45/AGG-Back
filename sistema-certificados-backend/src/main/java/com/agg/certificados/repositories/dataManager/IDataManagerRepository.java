package com.agg.certificados.repositories.dataManager;

import com.agg.certificados.entity.DataManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDataManagerRepository extends JpaRepository<DataManager,Long> {
}
