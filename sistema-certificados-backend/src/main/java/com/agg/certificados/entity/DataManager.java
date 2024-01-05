package com.agg.certificados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "data_manager")
public class DataManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_data_manager;
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

    public Long getId_data_manager() {
        return id_data_manager;
    }

    public void setId_data_manager(Long id_data_manager) {
        this.id_data_manager = id_data_manager;
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
}
