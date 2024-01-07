package com.agg.certificados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "price_rcd")
public class PriceRcd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_price_rcd;
    public Double price_m3;
    public Double total_price;
    @ManyToOne
    @JoinColumn(name = "data_generator_id")
    public DataGenerator data_generator_id;

}
