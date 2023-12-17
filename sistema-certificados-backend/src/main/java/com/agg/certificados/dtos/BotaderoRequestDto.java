
package com.agg.certificados.dtos;

import java.util.Date;

public class BotaderoRequestDto {
    public int id_botadero;
    public String city;
    public String property_name;
    public Date create_date;
    public int user_id;
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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
