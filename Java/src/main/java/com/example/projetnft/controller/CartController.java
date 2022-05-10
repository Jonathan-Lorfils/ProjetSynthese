package com.example.projetnft.controller;

import com.example.projetnft.model.Nft;
import com.example.projetnft.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PutMapping("/addItemToCart/{customerCartId}/{nftToAddId}")
    public Boolean addItem(@PathVariable Integer customerCartId, @PathVariable Integer nftToAddId){ // ajouter verification voir si l'item n'est pas deja dans le panier
        return cartService.addItemToCart(customerCartId, nftToAddId);
    }

    @PutMapping("/removeItemFromCart/{customerCartId}/{nftToAddId}")
    public Boolean removeItemFromCart(@PathVariable Integer customerCartId, @PathVariable Integer nftToAddId){
        return cartService.removeItemFromCart(customerCartId, nftToAddId);
    }

    @GetMapping("/getItems/{customerCartId}")
    public ResponseEntity<List<Nft>> getItemsFromCart(@PathVariable Integer customerCartId){
        return cartService.getItems(customerCartId)
                .map(cart1 -> ResponseEntity.status(HttpStatus.OK).body(cart1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("/getTotalPrice/{customerCartId}")
    public double getTotalPrice(@PathVariable Integer customerCartId){
        return cartService.getTotalPrice(customerCartId);
    }

    @PutMapping("/validateCart/{idNewOwner}/{customerCartId}")
    public Boolean validateCart(@PathVariable Integer idNewOwner, @PathVariable Integer customerCartId){
        return cartService.validateCart(idNewOwner, customerCartId);
    }
}
