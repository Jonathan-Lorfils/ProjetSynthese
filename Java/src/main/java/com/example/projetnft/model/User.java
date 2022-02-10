package com.example.projetnft.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String walletAddress;
    private double solde;
    private String password;
    private boolean sellerCertification;
}
