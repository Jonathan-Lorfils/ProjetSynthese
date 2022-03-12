package com.example.projetnft.controller;

import com.example.projetnft.model.Customer;
import com.example.projetnft.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return customerService.customerLogin(username, password)
                .map(user1 -> ResponseEntity.status(HttpStatus.OK).body(user1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(new Customer()));
    }

    @GetMapping("addfunds/{fundToAdd}/{phoneNumber}")
    public ResponseEntity<Customer> addfunds(@PathVariable Double fundToAdd, @PathVariable String phoneNumber){
        return customerService.addFunds(fundToAdd, phoneNumber)
                .map(customer1 -> ResponseEntity.status(HttpStatus.OK).body(customer1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(new Customer()));
    }

    @GetMapping("withdrawfunds/{fundToRemove}/{phoneNumber}")
    public ResponseEntity<Customer> withdrawFunds(@PathVariable Double fundToRemove, @PathVariable String phoneNumber){
        return customerService.withdrawFunds(fundToRemove, phoneNumber)
                .map(customer1 -> ResponseEntity.status(HttpStatus.OK).body(customer1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(new Customer()));
    }

    @GetMapping("requestSellerCertification/{phoneNumber}")
    public ResponseEntity<Customer> requestSellerCertification(@PathVariable String phoneNumber){
        return customerService.requestSellerCertification(phoneNumber)
                .map(customer1 -> ResponseEntity.status(HttpStatus.OK).body(customer1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(new Customer()));
    }

    @GetMapping("/getAllCustomersWaitingForCertification")
    public ResponseEntity<List<Customer>> getAllCustomersWaitingForCertification(){
        return customerService.getAllCustomersWaitingForCertification()
                .map(customer1 -> ResponseEntity.status(HttpStatus.OK).body(customer1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
