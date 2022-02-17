package com.example.projetnft.controller;

import com.example.projetnft.model.Customer;
import com.example.projetnft.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerUser(@RequestBody Customer customer){
        return customerService.registerUser(customer)
                .map(user1 -> ResponseEntity.status(HttpStatus.CREATED).body(user1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("/{username}/{password}")
    public ResponseEntity<Customer> loginUser(@PathVariable String username, @PathVariable String password){
        return customerService.userLogin(username, password)
                .map(user1 -> ResponseEntity.status(HttpStatus.OK).body(user1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(new Customer()));
    }
}
