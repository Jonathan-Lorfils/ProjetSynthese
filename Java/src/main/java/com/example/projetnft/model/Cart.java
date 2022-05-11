package com.example.projetnft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Builder
public class Cart implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private double totalprice = 0;
    @ManyToMany
    @JsonIgnore
    private List<Nft> items = new ArrayList<>();

    @Builder(builderMethodName = "cartBuilder")
    public Cart(Integer id, double totalprice, List<Nft> items){
        this.id = id;
        this.totalprice = totalprice;
        this.items = items;
    }
}