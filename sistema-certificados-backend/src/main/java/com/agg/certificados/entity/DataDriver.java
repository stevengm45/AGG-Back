package com.agg.certificados.entity;

import javax.persistence.*;

@Entity
@Table(name = "data_driver")
public class DataDriver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_data_driver;
    @OneToOne
    @JoinColumn(name = "data_generator_id")
    public DataGenerator data_generator_id;
    public String name;
    @ManyToOne
    @JoinColumn(name = "type_document_id")
    public TypeDocument type_document_id;
    public String number_id;
    public String vehicle_plate;

    public DataDriver() {
    }

    public DataDriver(Long id_data_driver, DataGenerator data_generator_id, String name, TypeDocument type_document_id, String number_id, String vehicle_plate) {
        this.id_data_driver = id_data_driver;
        this.data_generator_id = data_generator_id;
        this.name = name;
        this.type_document_id = type_document_id;
        this.number_id = number_id;
        this.vehicle_plate = vehicle_plate;
    }

    public Long getId_data_driver() {
        return id_data_driver;
    }

    public void setId_data_driver(Long id_data_driver) {
        this.id_data_driver = id_data_driver;
    }

    public DataGenerator getData_generator_id() {
        return data_generator_id;
    }

    public void setData_generator_id(DataGenerator data_generator_id) {
        this.data_generator_id = data_generator_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeDocument getType_document_id() {
        return type_document_id;
    }

    public void setType_document_id(TypeDocument type_document_id) {
        this.type_document_id = type_document_id;
    }

    public String getNumber_id() {
        return number_id;
    }

    public void setNumber_id(String number_id) {
        this.number_id = number_id;
    }

    public String getVehicle_plate() {
        return vehicle_plate;
    }

    public void setVehicle_plate(String vehicle_plate) {
        this.vehicle_plate = vehicle_plate;
    }
}
