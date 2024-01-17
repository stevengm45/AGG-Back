package com.agg.certificados.dtos.response;

import javax.persistence.Entity;
import java.math.BigInteger;
import java.util.Date;

@Entity
public class BandejaCertificacionesResponseDto {

    public BigInteger id_certification;
    public String number_certification;
    public Date create_date;
    public String name;
    public String number_id;
    public String file_certificate_botadero;
    public String file_certificate_bascula;

    public BandejaCertificacionesResponseDto(){

    }
    public BandejaCertificacionesResponseDto(BigInteger id_certification, String number_certification, Date create_date, String name, String number_id, String file_certificate_botadero, String file_certificate_bascula) {
        this.id_certification = id_certification;
        this.number_certification = number_certification;
        this.create_date = create_date;
        this.name = name;
        this.number_id = number_id;
        this.file_certificate_botadero = file_certificate_botadero;
        this.file_certificate_bascula = file_certificate_bascula;
    }

    public BigInteger getId_certification() {
        return id_certification;
    }

    public void setId_certification(BigInteger id_certification) {
        this.id_certification = id_certification;
    }

    public String getNumber_certification() {
        return number_certification;
    }

    public void setNumber_certification(String number_certification) {
        this.number_certification = number_certification;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber_id() {
        return number_id;
    }

    public void setNumber_id(String number_id) {
        this.number_id = number_id;
    }

    public String getFile_certificate_botadero() {
        return file_certificate_botadero;
    }

    public void setFile_certificate_botadero(String file_certificate_botadero) {
        this.file_certificate_botadero = file_certificate_botadero;
    }

    public String getFile_certificate_bascula() {
        return file_certificate_bascula;
    }

    public void setFile_certificate_bascula(String file_certificate_bascula) {
        this.file_certificate_bascula = file_certificate_bascula;
    }
}
