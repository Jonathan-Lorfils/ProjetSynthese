package com.example.projetnft.controller;

import com.example.projetnft.model.Cart;
import com.example.projetnft.repository.CartRepository;
import com.example.projetnft.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PutMapping("/addItemToCart/{customerCartId}/{nftToAddId}")
    public ResponseEntity<Cart> addItem(@PathVariable Integer customerCartId, @PathVariable Integer nftToAddId){
        return cartService.addItemToCart(customerCartId, nftToAddId)
                .map(cart1 -> ResponseEntity.status(HttpStatus.OK).body(cart1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PutMapping("/removeItemFromCart/{customerCartId}/{nftToAddId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Integer customerCartId, @PathVariable Integer nftToAddId){
        return cartService.addItemToCart(customerCartId, nftToAddId)
                .map(cart1 -> ResponseEntity.status(HttpStatus.OK).body(cart1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
