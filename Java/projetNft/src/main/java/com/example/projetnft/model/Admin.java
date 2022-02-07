package com.example.projetnft.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Admin {
    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String password;
}
