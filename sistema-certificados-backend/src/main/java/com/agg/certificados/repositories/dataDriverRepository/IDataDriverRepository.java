package com.agg.certificados.repositories.dataDriverRepository;

import com.agg.certificados.entity.DataDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IDataDriverRepository extends JpaRepository<DataDriver, Long> {

    @Query(value = "SELECT d.* FROM data_driver AS d WHERE d.data_generator_id = :idDataGenerator",nativeQuery = true)
    public DataDriver findByIdDataGenerator(Long idDataGenerator);

}
