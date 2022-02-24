package com.example.projetnft.service;

import com.example.projetnft.model.Customer;
import com.example.projetnft.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    public CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> registerUser(Customer customer){
        try{
            return Optional.of(customerRepository.save(customer));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Customer> userLogin(String username, String password){
        try {
            return Optional.of(customerRepository.findByUsernameAndPassword(username, password));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public Optional<Customer> addfunds(Double fundToAdd, String phoneNumber){
        try {
            Customer customer = customerRepository.findByPhoneNumber(phoneNumber);
            customer.setSolde(customer.getSolde() + fundToAdd);
            customerRepository.save(customer);
            return Optional.of(customer);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
