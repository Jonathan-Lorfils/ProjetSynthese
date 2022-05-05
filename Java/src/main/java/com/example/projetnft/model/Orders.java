package com.example.projetnft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Orders implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer orderNumber;

    private double price;

    private Integer customerId;

    @Builder(builderMethodName = "orderBuilder")
    public Orders(Integer id, Integer orderNumber, Integer customerId, double price) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.price = price;
    }
}
