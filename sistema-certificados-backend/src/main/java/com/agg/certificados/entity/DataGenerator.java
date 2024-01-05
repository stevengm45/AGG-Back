package com.agg.certificados.entity;

import com.agg.certificados.dtos.request.DataGeneratorRequestDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "data_generators")
public class DataGenerator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_data_generator;

    @ManyToOne
    @JoinColumn(name = "botadero_id")
    public Botadero botadero_id;

    //Foranea del data_manager
    @ManyToOne
    @JoinColumn(name = "data_manager_id")
    public DataManager data_manager_id;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    public Manager manager_id;
    public String unic_number;
    public String name;
    @ManyToOne
    @JoinColumn(name = "type_document_id")
    public TypeDocument type_document_id;
    public String number_id;
    public String legal_representative;
    public String address;
    public Long phone_number;
    public String email;
    public String address_rcd;
    public Long total_rcd;
    public String reception_date_rcd;
    public DataGenerator(){

    }

    public DataGenerator(Long id_data_generator, Botadero botadero_id, DataManager data_manager_id, Manager manager_id, String unic_number, String name, TypeDocument type_document_id, String number_id, String legal_representative, String address, Long phone_number, String email, String address_rcd, Long total_rcd, String reception_date_rcd) {
        this.id_data_generator = id_data_generator;
        this.botadero_id = botadero_id;
        this.data_manager_id = data_manager_id;
        this.manager_id = manager_id;
        this.unic_number = unic_number;
        this.name = name;
        this.type_document_id = type_document_id;
        this.number_id = number_id;
        this.legal_representative = legal_representative;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.address_rcd = address_rcd;
        this.total_rcd = total_rcd;
        this.reception_date_rcd = reception_date_rcd;
//        QuantitiesRcd = quantitiesRcd;
//        this.data_driver = data_driver;
    }

    public Long getId_data_generator() {
        return id_data_generator;
    }

    public void setId_data_generator(Long id_data_generator) {
        this.id_data_generator = id_data_generator;
    }

    public Botadero getBotadero_id() {
        return botadero_id;
    }

    public void setBotadero_id(Botadero botadero_id) {
        this.botadero_id = botadero_id;
    }

    public DataManager getData_manager_id() {
        return data_manager_id;
    }

    public void setData_manager_id(DataManager data_manager_id) {
        this.data_manager_id = data_manager_id;
    }

    public Manager getManager_id() {
        return manager_id;
    }

    public void setManager_id(Manager manager_id) {
        this.manager_id = manager_id;
    }

    public String getUnic_number() {
        return unic_number;
    }

    public void setUnic_number(String unic_number) {
        this.unic_number = unic_number;
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

    public String getLegal_representative() {
        return legal_representative;
    }

    public void setLegal_representative(String legal_representative) {
        this.legal_representative = legal_representative;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress_rcd() {
        return address_rcd;
    }

    public void setAddress_rcd(String address_rcd) {
        this.address_rcd = address_rcd;
    }

    public Long getTotal_rcd() {
        return total_rcd;
    }

    public void setTotal_rcd(Long total_rcd) {
        this.total_rcd = total_rcd;
    }

    public String getReception_date_rcd() {
        return reception_date_rcd;
    }

    public void setReception_date_rcd(String reception_date_rcd) {
        this.reception_date_rcd = reception_date_rcd;
    }
/*

    public List<com.agg.certificados.entity.QuantitiesRcd> getQuantitiesRcd() {
        return QuantitiesRcd;
    }

    public void setQuantitiesRcd(List<com.agg.certificados.entity.QuantitiesRcd> quantitiesRcd) {
        QuantitiesRcd = quantitiesRcd;
    }

    public DataDriver getData_driver() {
        return data_driver;
    }

    public void setData_driver(DataDriver data_driver) {
        this.data_driver = data_driver;
    }
*/
}
