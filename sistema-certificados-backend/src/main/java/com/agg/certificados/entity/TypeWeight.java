package com.agg.certificados.entity;

import javax.persistence.*;

@Entity
@Table(name = "type_weights")
public class TypeWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String name;
    public String description;
    public boolean status;

    public TypeWeight() {
    }

    public TypeWeight(Long id_type_weight, String description, boolean status, String name) {
        this.description = description;
        this.status = status;
        this.name = name;
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
