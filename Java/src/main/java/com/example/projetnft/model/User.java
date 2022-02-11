package com.example.projetnft.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String walletAddress;
    private double solde = 0;
    private String password;
    private boolean sellerCertification = false;

    @Builder
    public User(int id, String firstName, String lastName, String phone, String email, String walletAddress, double solde, String password, boolean sellerCertification) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phone;
        this.email = email;
        this.walletAddress = walletAddress;
        this.solde = solde;
        this.password = password;
        this.sellerCertification = sellerCertification;
    }


}
