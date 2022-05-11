package com.example.projetnft.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Customer extends User implements Serializable {

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String walletAddress;
    private Double solde = 0.0;
    private String sellerCertification = "Invalide";
    @OneToOne
    private Cart cart;

    @Builder(builderMethodName = "customerBuilder")
    public Customer(Integer id, String password, String username, String firstName, String lastName, String phone, String email, String walletAddress, double solde, String sellerCertification, Cart cart) {
        super.setId(id);
        super.setUsername(username);
        super.setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phone;
        this.email = email;
        this.walletAddress = walletAddress;
        this.solde = solde;
        this.sellerCertification = sellerCertification;
        this.cart = cart;
    }
}