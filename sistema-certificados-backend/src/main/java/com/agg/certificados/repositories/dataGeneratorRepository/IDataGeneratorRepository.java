package com.agg.certificados.repositories.dataGeneratorRepository;

import com.agg.certificados.entity.DataGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDataGeneratorRepository extends JpaRepository<DataGenerator, Long> {
    @Query(value = "SELECT d.* FROM data_generators AS d JOIN certification AS c ON d.id_data_generator = c.data_generator_id WHERE d.botadero_id = :id AND (c.create_date >= :start_date AND c.create_date <= :end_date)",nativeQuery = true)

    List<DataGenerator> getbyIdBotadero(int id, String start_date, String end_date);
}
