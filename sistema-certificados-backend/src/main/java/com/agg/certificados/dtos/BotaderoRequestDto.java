
package com.agg.certificados.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class BotaderoRequestDto {
    public int id_botadero;
    public String city;
    public String property_name;
    @JsonIgnore
    public LocalDate create_date;
    public long user_id;
    public boolean status;

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

    public LocalDate getCreate_date() {
        //return create_date;
        return LocalDate.now();
    }

    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
