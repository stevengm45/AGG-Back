package com.agg.certificados.domain.models;

import jakarta.persistence.*;

@Entity
@Table(name = "type_documents")
public class TypeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id_type_document;
    public String name;
    public String description;
    public boolean status;

    public TypeDocument() {
    }

    public TypeDocument(int id_type_document, String description, boolean status, String name) {
        this.id_type_document = id_type_document;
        this.description = description;
        this.status = status;
        this.name = name;
    }

    public int getId_type_document() {
        return id_type_document;
    }

    public void setId_type_document(int id_type_document) {
        this.id_type_document = id_type_document;
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
