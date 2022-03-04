package com.example.projetnft.service;

import com.example.projetnft.model.Customer;
import com.example.projetnft.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;
    private Customer customerRegistred;

    @BeforeEach
    void setup(){
        customer = Customer.builder()
                .id(1)
                .email("test@gmail.com")
                .firstName("Jojo")
                .lastName("Lolo")
                .password("12345")
                .phone("5141234321")
                .solde(32.0)
                .walletAddress("ajbdgoge2o8gojn309")
                .build();

        customerRegistred = Customer.builder()
                .id(1)
                .email("test@gmail.com")
                .firstName("Jojo")
                .lastName("Lolo")
                .password("12345")
                .phone("5141234321")
                .solde(32.0)
                .walletAddress("ajbdgoge2o8gojn309")
                .build();
    }

    @Test
    public void testRegisterUser(){
        when(customerRepository.save(customer)).thenReturn(customerRegistred);
        Optional<Customer> actualUser = customerService.registerUser(customer);
        assertThat(actualUser.get()).isEqualTo(customerRegistred);
    }

    @Test
    public void testLoginUser(){
        when(customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword())).thenReturn(customer);
        Optional<Customer> actualUser = customerService.customerLogin(customer.getUsername(), customer.getPassword());
        assertThat(actualUser.get()).isEqualTo(customer);
    }

    @Test
    public void testAddFunds(){
        when(customerRepository.findByPhoneNumber(customer.getPhoneNumber())).thenReturn(customer);
        Optional<Customer> actualCustomer = customerService.addFunds(1.0, customer.getPhoneNumber());
        assertThat(actualCustomer.get()).isEqualTo(customer);
    }

    @Test
    public void testWithdrawFunds(){
        when(customerRepository.findByPhoneNumber(customer.getPhoneNumber())).thenReturn(customer);
        Optional<Customer> actualCustomer = customerService.withdrawFunds(1.0, customer.getPhoneNumber());
        assertThat(actualCustomer.get()).isEqualTo(customer);
    }

    @Test
    public void testRequestSellerCertification(){
        when(customerRepository.findByPhoneNumber(customer.getPhoneNumber())).thenReturn(customer);
        Optional<Customer> actualCustomer = customerService.requestSellerCertification(customer.getPhoneNumber());
        assertThat(actualCustomer.get().getSellerCertification()).isEqualTo(customer.getSellerCertification());
    }
}
