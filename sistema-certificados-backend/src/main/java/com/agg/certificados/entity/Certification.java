package com.agg.certificados.entity;

import javax.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "certification")
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_certification;
    @ManyToOne
    @JoinColumn(name = "data_generator_id")
    public DataGenerator data_generator_id;
    @Column(columnDefinition = "LONGTEXT")
    public String fileCertificateBotadero;
    @Column(columnDefinition = "LONGTEXT")
    public String fileCertificateBascula;
    public LocalDate create_date;
    public Long number_certification;
    public String final_number_certification;

    public Long getId_certification() {
        return id_certification;
    }

    public void setId_certification(Long id_certification) {
        this.id_certification = id_certification;
    }

    public DataGenerator getData_generator_id() {
        return data_generator_id;
    }

    public void setData_generator_id(DataGenerator data_generator_id) {
        this.data_generator_id = data_generator_id;
    }

    public String getFileCertificateBotadero() {
        return fileCertificateBotadero;
    }

    public void setFileCertificateBotadero(String fileCertificateBotadero) {
        this.fileCertificateBotadero = fileCertificateBotadero;
    }

    public String getFileCertificateBascula() {
        return fileCertificateBascula;
    }

    public void setFileCertificateBascula(String fileCertificateBascula) {
        this.fileCertificateBascula = fileCertificateBascula;
    }

    public LocalDate getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
    }

    public Long getNumber_certification() {
        return number_certification;
    }

    public void setNumber_certification(Long number_certification) {
        this.number_certification = number_certification;
    }

    public String getFinal_number_certification() {
        return final_number_certification;
    }

    public void setFinal_number_certification(String final_number_certification) {
        this.final_number_certification = final_number_certification;
    }
}
