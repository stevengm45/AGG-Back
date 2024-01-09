package com.agg.certificados.repositories.certificationRepository;

import com.agg.certificados.entity.Certification;
import com.agg.certificados.entity.DataDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICertificationRepository extends JpaRepository<Certification,Long> {
    @Query(value = "SELECT MAX(c.number_certification) FROM certification AS c WHERE c.data_generator_id = :idDataGenerator AND YEAR(c.create_date) = :anio",nativeQuery = true)
    Long findByMaxNumberCertification(Long idDataGenerator, int anio);

}
