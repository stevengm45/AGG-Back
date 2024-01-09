package com.agg.certificados.repositories.dataGeneratorRepository;

import com.agg.certificados.entity.Botadero;
import com.agg.certificados.entity.DataGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDataGeneratorRepository extends JpaRepository<DataGenerator, Long> {
}
