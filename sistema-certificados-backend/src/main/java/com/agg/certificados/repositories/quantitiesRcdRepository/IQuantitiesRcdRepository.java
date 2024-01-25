package com.agg.certificados.repositories.quantitiesRcdRepository;

import com.agg.certificados.entity.QuantitiesRcd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IQuantitiesRcdRepository extends JpaRepository<QuantitiesRcd,Long> {
    @Query(value = "SELECT q.* FROM quantities_rcd AS q WHERE q.data_generator_id = :idDataGenerator",nativeQuery = true)
    public List<QuantitiesRcd> findByIdDataGenerator(Long idDataGenerator);

    @Query(value = "SELECT q.* FROM quantities_rcd AS q WHERE q.data_generator_id = :idDataGenerator AND q.type_rcd_id = :idTypeRcd",nativeQuery = true)
    QuantitiesRcd findByIdDataGeneratorAndIdTypeRcd(Long idDataGenerator, Long idTypeRcd);

}
