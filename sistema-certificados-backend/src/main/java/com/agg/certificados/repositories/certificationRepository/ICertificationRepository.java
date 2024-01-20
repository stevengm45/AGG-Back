package com.agg.certificados.repositories.certificationRepository;

import com.agg.certificados.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ICertificationRepository extends JpaRepository<Certification,Long> {
    @Query(value = "SELECT MAX(c.number_certification) FROM certification AS c WHERE YEAR(c.create_date) = :anio",nativeQuery = true)
    Long findByMaxNumberCertification(int anio);
    @Query(value = "{call sp_bandejacertificaciones(:create_date,:number_certification,:number_id)}", nativeQuery = true)
    List<Object[]> getBandejaCertifications(@Nullable String create_date,
                                            @Nullable String number_certification,
                                            @Nullable String number_id);
    @Query(value = "SELECT c.* FROM certification AS c WHERE c.data_generator_id = :id",nativeQuery = true)

    Certification findByIdDataGenerator(Long id);
}
