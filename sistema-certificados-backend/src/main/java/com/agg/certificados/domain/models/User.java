package com.agg.certificados.domain.models;

import jakarta.persistence.*;

import java.awt.image.Kernel;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id_user;
    public String full_name;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "type_document_id")
    public TypeDocument type_document_id;
    public long number_id;
    public String email;
    public String user_name;
    public String password;
    public Date create_date;
    public boolean status;

    public User() {
    }

    public User(int id_user, String full_name, TypeDocument type_document_id, long number_id, String email, String user_name, String password, Date create_date, boolean status) {
        this.id_user = id_user;
        this.full_name = full_name;
        this.type_document_id = type_document_id;
        this.number_id = number_id;
        this.email = email;
        this.user_name = user_name;
        this.password = password;
        this.create_date = create_date;
        this.status = status;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public TypeDocument getType_document_id() {
        return type_document_id;
    }

    public void setType_document_id(TypeDocument type_document_id) {
        this.type_document_id = type_document_id;
    }

    public long getNumber_id() {
        return number_id;
    }

    public void setNumber_id(long number_id) {
        this.number_id = number_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
