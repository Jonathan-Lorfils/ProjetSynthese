package com.example.projetnft.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Admin extends User implements Serializable {

    @Builder
    public Admin(Integer id, String username, String password) {
        super.setId(id);
        super.setUsername(username);
        super.setPassword(password);
    }
}
