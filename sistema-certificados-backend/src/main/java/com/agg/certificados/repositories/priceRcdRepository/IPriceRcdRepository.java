package com.agg.certificados.repositories.priceRcdRepository;

import com.agg.certificados.entity.DataDriver;
import com.agg.certificados.entity.PriceRcd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPriceRcdRepository extends JpaRepository<PriceRcd, Long> {
    @Query(value = "SELECT p.* FROM price_rcd AS p WHERE p.data_generator_id = :idDataGenerator",nativeQuery = true)
    PriceRcd findByIdDataGenerator(Long idDataGenerator);
}
