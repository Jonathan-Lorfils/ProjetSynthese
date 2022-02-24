package com.example.projetnft.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Customer extends User implements Serializable {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String walletAddress;
    private Double solde = 0.0;
    private boolean sellerCertification = false;

    @Builder
    public Customer(int id, String password, String username, String firstName, String lastName, String phone, String email, String walletAddress, double solde, boolean sellerCertification) {
        super.setId(id);
        super.setUsername(username);
        super.setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phone; // unique
        this.email = email; // mettre unique
        this.walletAddress = walletAddress;
        this.solde = solde;
        this.sellerCertification = sellerCertification;
    }


}
