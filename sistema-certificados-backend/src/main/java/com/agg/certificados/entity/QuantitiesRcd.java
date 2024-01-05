package com.agg.certificados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quantities_rcd")
public class QuantitiesRcd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_quantities_rcd;
    @ManyToOne
    @JoinColumn(name = "type_rcd_id")
    public TypeRcd type_rcd_id;
    @ManyToOne
    @JoinColumn(name = "data_generator_id")
    public DataGenerator data_generator_id;
    public Long quantity_rcd;

    public Long getId_quantities_rcd() {
        return id_quantities_rcd;
    }

    public void setId_quantities_rcd(Long id_quantities_rcd) {
        this.id_quantities_rcd = id_quantities_rcd;
    }

    public TypeRcd getType_rcd_id() {
        return type_rcd_id;
    }

    public void setType_rcd_id(TypeRcd type_rcd_id) {
        this.type_rcd_id = type_rcd_id;
    }

    public DataGenerator getData_generator_id() {
        return data_generator_id;
    }

    public void setData_generator_id(DataGenerator data_generator_id) {
        this.data_generator_id = data_generator_id;
    }

    public Long getQuantity_rcd() {
        return quantity_rcd;
    }

    public void setQuantity_rcd(Long quantity_rcd) {
        this.quantity_rcd = quantity_rcd;
    }
}
