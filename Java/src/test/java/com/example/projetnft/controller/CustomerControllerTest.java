package com.example.projetnft.controller;

import com.example.projetnft.model.Customer;
import com.example.projetnft.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private Customer customer;

    private Customer newSoldeCustomer;

    @BeforeEach
    void setup(){
        customer = Customer.builder()
                .id(1)
                .username("toto")
                .email("test@gmail.com")
                .firstName("Jojo")
                .lastName("Lolo")
                .password("12345")
                .phone("5141234321")
                .solde(32.0)
                .walletAddress("ajbdgoge2o8gojn309")
                .build();

        newSoldeCustomer = Customer.builder()
                .id(1)
                .username("toto")
                .email("test@gmail.com")
                .firstName("Jojo")
                .lastName("Lolo")
                .password("12345")
                .phone("5141234321")
                .solde(33.0)
                .walletAddress("ajbdgoge2o8gojn309")
                .build();

    }

    @Test
    public void registerUserTest() throws Exception {
        when(customerService.registerUser(customer)).thenReturn(Optional.of(customer));

        MvcResult result = mockMvc.perform(post("/customer/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(customer))).andReturn();

        var actualCustomer = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Customer.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualCustomer).isEqualTo(customer);
    }

    @Test
    public void loginUserTest() throws Exception {
        when(customerService.userLogin(customer.getUsername(), customer.getPassword())).thenReturn(Optional.of(customer));

        MvcResult result = mockMvc.perform(get("/customer/{username}/{password}", customer.getUsername(), customer.getPassword())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualCustomer = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Customer.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualCustomer).isEqualTo(customer);
    }

    @Test
    public void addFundTest() throws Exception {
        when(customerService.addFunds(1.0, customer.getPhoneNumber())).thenReturn(Optional.of(newSoldeCustomer));

        MvcResult result = mockMvc.perform(get("/customer/addfunds/{fundToAdd}/{phoneNumber}", 1.0, customer.getPhoneNumber())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualCustomer = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Customer.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualCustomer.getSolde()).isEqualTo(33.0);
    }

    @Test
    public void withdrawFundsTest() throws Exception {
        when(customerService.withdrawFunds(1.0, customer.getPhoneNumber())).thenReturn(Optional.of(newSoldeCustomer));

        MvcResult result = mockMvc.perform(get("/customer/withdrawfunds/{fundToRemove}/{phoneNumber}", 1.0, customer.getPhoneNumber())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualCustomer = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Customer.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualCustomer.getSolde()).isEqualTo(33.0);
    }
}
