package com.example.projetnft.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Orders implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private double price;
    private String status;
    private LocalDate date;
    @ManyToOne
    private Customer customer;

    @Builder(builderMethodName = "orderBuilder")
    public Orders(Integer id, Customer customer, double price) {
        this.id = id;
        this.customer = customer;
        this.price = price;
    }
}