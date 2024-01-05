package com.agg.certificados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_rcd")
public class TypeRcd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_type_rcd;
    public String name;
    public String description;
    public Boolean status;

    public Long getId_type_rcd() {
        return id_type_rcd;
    }

    public void setId_type_rcd(Long id_type_rcd) {
        this.id_type_rcd = id_type_rcd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
