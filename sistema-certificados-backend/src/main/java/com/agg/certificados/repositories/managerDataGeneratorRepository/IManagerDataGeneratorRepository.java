package com.agg.certificados.repositories.managerDataGeneratorRepository;

import com.agg.certificados.entity.ManagerDataGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IManagerDataGeneratorRepository extends JpaRepository<ManagerDataGenerator, Long> {
    @Query(value = "SELECT m.* FROM manager_data_generator AS m WHERE m.data_generator_id = :idDataGenerator",nativeQuery = true)
    List<ManagerDataGenerator> findByIdDataGenerator(Long idDataGenerator);
    @Query(value = "delete from manager_data_generator where data_generator_id = :idDataGenerator", nativeQuery = true)
    boolean deleteByIdDataGenerator(Long idDataGenerator);
}
