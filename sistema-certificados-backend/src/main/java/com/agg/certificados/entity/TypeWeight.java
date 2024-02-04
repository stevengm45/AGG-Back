package com.agg.certificados.entity;

import javax.persistence.*;

@Entity
@Table(name = "type_weights")
public class TypeWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_type_weight;
    public String name;
    public String description;
    public boolean status;

    public TypeWeight() {
    }

    public TypeWeight(Long id_type_weight,String description, boolean status, String name) {
        this.id_type_weight = id_type_weight;
        this.description = description;
        this.status = status;
        this.name = name;
    }

    public Long getId_type_weight() {
        return id_type_weight;
    }

    public void setId_type_weight(Long id_type_weight) {
        this.id_type_weight = id_type_weight;
    }

    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
