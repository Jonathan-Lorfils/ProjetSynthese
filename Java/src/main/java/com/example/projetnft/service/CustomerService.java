package com.example.projetnft.service;

import com.example.projetnft.model.Cart;
import com.example.projetnft.model.Customer;
import com.example.projetnft.repository.CartRepository;
import com.example.projetnft.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    public CustomerRepository customerRepository;

    public CartRepository cartRepository;

    public CustomerService(CustomerRepository customerRepository, CartRepository cartRepository){
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    public Optional<Customer> registerUser(Customer customer){
        try{
            if(!customerRepository.existsByPhoneNumberOrEmail(customer.getPhoneNumber(), customer.getEmail())){
                Cart newCart = new Cart();
                customer.setCart(newCart);
                cartRepository.save(newCart);
                return Optional.of(customerRepository.save(customer));
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Customer> customerLogin(String username, String password){
        try {
            return Optional.of(customerRepository.findByUsernameAndPassword(username, password));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public Optional<Customer> addFunds(Double fundToAdd, String phoneNumber){
        try {
            Customer customer = customerRepository.findByPhoneNumber(phoneNumber);
            customer.setSolde(customer.getSolde() + fundToAdd);
            customerRepository.save(customer);
            return Optional.of(customer);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public Optional<Customer> withdrawFunds(Double fundToRemove, String phoneNumber){ // verification solde negatif cote front end
        try {
            Customer customer = customerRepository.findByPhoneNumber(phoneNumber);
            customer.setSolde(customer.getSolde() - fundToRemove);
            customerRepository.save(customer);
            return Optional.of(customer);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public Optional<List<Customer>> getAllCustomersWaitingForCertification() {
        return Optional.of(customerRepository.getAllBySellerCertification("En attente"));
    }

    public Optional<Customer> setCustomerSellerCertification(String phoneNumber, String state) {
        try {
            Customer customer = customerRepository.findByPhoneNumber(phoneNumber);
            customer.setSellerCertification(state);
            customerRepository.save(customer);
            return Optional.of(customer);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public Optional<Customer> getCustomerInfoById(Integer idCustomer){
        try {
            return Optional.of(customerRepository.findById(idCustomer).get());
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}