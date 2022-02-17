package com.example.projetnft.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Nft {

    @Id
    @GeneratedValue
    private int id;

    @Lob
    private byte[] data;

    private boolean certified;

    @OneToOne
    private Customer owner;
}
