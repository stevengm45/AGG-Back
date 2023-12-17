package com.agg.certificados.entity;

import com.agg.certificados.domain.models.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "botaderos")
public class Botadero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id_botadero;
    public String city;
    public String property_name;
    public Date create_date;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "user_id")
    public com.agg.certificados.domain.models.User user_id;
    public boolean status;

    public Botadero(){
    }

    public Botadero(int id_botadero, String city, String property_name, Date create_date, com.agg.certificados.domain.models.User user_id, boolean status) {
        this.id_botadero = id_botadero;
        this.city = city;
        this.property_name = property_name;
        this.create_date = create_date;
        this.user_id = user_id;
        this.status = status;
    }

    public int getId_botadero() {
        return id_botadero;
    }

    public void setId_botadero(int id_botadero) {
        this.id_botadero = id_botadero;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProperty_name() {
        return property_name;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public com.agg.certificados.domain.models.User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
