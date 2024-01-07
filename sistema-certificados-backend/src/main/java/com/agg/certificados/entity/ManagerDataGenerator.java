package com.agg.certificados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "manager_data_generator")
public class ManagerDataGenerator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_manager_data_generator;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    public Manager manager_id;
    @ManyToOne
    @JoinColumn(name = "data_generator_id")
    public DataGenerator data_generator_id;

    public ManagerDataGenerator(Long id_manager_data_generator, Manager manager_id, DataGenerator data_generator_id) {
        this.id_manager_data_generator = id_manager_data_generator;
        this.manager_id = manager_id;
        this.data_generator_id = data_generator_id;
    }
    public ManagerDataGenerator(){

    }
}
