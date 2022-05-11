package com.example.projetnft.controller;

import com.example.projetnft.model.Orders;
import com.example.projetnft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getAllOrdersByCustomer/{customerId}")
    public ResponseEntity<List<Orders>> getAllOrdersByCustomer(@PathVariable Integer customerId){
        return orderService.getOrdersbyCustomer(customerId)
                .map(nft1 -> ResponseEntity.status(HttpStatus.OK).body(nft1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
