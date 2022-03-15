package com.example.projetnft.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Nft implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Lob
    private byte[] data;

    private boolean certified = false;

    private boolean toSell = false; // yes = disponible a la vente / no = pas de vente

    private double price;

    private String name;

    @OneToOne
    private Customer owner;

    @Builder(builderMethodName = "nftBuilder")
    public Nft(int id, byte[] data, boolean certified, boolean toSell, double price, String name, Customer owner) {
        this.id = id;
        this.data = data;
        this.certified = certified;
        this.toSell = toSell;
        this.price = price;
        this.name = name;
        this.owner = owner;
    }
}
