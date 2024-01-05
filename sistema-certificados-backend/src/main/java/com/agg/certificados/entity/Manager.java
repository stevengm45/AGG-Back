package com.agg.certificados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_manager;
    public String name;
    public Boolean status;

    public Long getId_manager() {
        return id_manager;
    }

    public void setId_manager(Long id_manager) {
        this.id_manager = id_manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
