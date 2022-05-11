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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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
        customer = Customer.customerBuilder()
                .id(1)
                .username("toto")
                .email("test@gmail.com")
                .firstName("Jojo")
                .lastName("Lolo")
                .password("12345")
                .phone("5141234321")
                .sellerCertification("Invalide")
                .solde(32.0)
                .walletAddress("ajbdgoge2o8gojn309")
                .build();

        newSoldeCustomer = Customer.customerBuilder()
                .id(1)
                .username("toto")
                .email("test@gmail.com")
                .firstName("Jojo")
                .lastName("Lolo")
                .password("12345")
                .phone("5141234321")
                .sellerCertification("En attente")
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
        when(customerService.customerLogin(customer.getUsername(), customer.getPassword())).thenReturn(Optional.of(customer));

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

        MvcResult result = mockMvc.perform(put("/customer/addfunds/{fundToAdd}/{phoneNumber}", 1.0, customer.getPhoneNumber())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualCustomer = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Customer.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualCustomer.getSolde()).isEqualTo(33.0);
    }

    @Test
    public void withdrawFundsTest() throws Exception {
        when(customerService.withdrawFunds(1.0, customer.getPhoneNumber())).thenReturn(Optional.of(newSoldeCustomer));

        MvcResult result = mockMvc.perform(put("/customer/withdrawfunds/{fundToRemove}/{phoneNumber}", 1.0, customer.getPhoneNumber())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualCustomer = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Customer.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualCustomer.getSolde()).isEqualTo(33.0);
    }

    @Test
    public void setCustomerSellerCertificationTest() throws Exception {
        when(customerService.setCustomerSellerCertification(customer.getPhoneNumber(), "En attente")).thenReturn(Optional.of(newSoldeCustomer));

        MvcResult result = mockMvc.perform(put("/customer/setCustomerSellerCertification/{phoneNumber}/{state}", customer.getPhoneNumber(), "En attente")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualCustomer = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Customer.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualCustomer.getSellerCertification()).isEqualTo("En attente");
    }

    @Test
    public void getAllCustomersWaitingForCertificationTest() throws Exception {
        when(customerService.getAllCustomersWaitingForCertification()).thenReturn(Optional.of(getListOfCustomers()));

        MvcResult result = mockMvc.perform(get("/customer/getAllCustomersWaitingForCertification")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actuals = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actuals.size()).isEqualTo(3);
    }

    @Test
    public void getCustomerInfoByIdTest() throws Exception {
        when(customerService.getCustomerInfoById(1)).thenReturn(Optional.of(customer));

        MvcResult result = mockMvc.perform(get("/customer/getCustomerInfoById/{idCustomer}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actuals = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Customer.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actuals).isEqualTo(customer);
    }

    private List<Customer> getListOfCustomers(){
        List<Customer> customerList = new ArrayList<>();
        customerList.add(Customer.customerBuilder()
                .id(1)
                .email("test@gmail.com")
                .firstName("Jojo")
                .lastName("Lolo")
                .password("12345")
                .phone("5141234321")
                .solde(32.0)
                .walletAddress("ajbdgoge2o8gojn309")
                .sellerCertification("En attente")
                .build());
        customerList.add(Customer.customerBuilder()
                .id(2)
                .email("test@gmail.com")
                .firstName("Toto")
                .lastName("Lala")
                .password("12345")
                .phone("5141244321")
                .solde(32.0)
                .walletAddress("ajbdgoge2o8gojn309")
                .sellerCertification("En attente")
                .build());
        customerList.add(Customer.customerBuilder()
                .id(3)
                .email("test@gmail.com")
                .firstName("Tutu")
                .lastName("Pepe")
                .password("ejkew")
                .phone("5149481053")
                .solde(32.0)
                .walletAddress("ajbdgoge2o8gojn309")
                .sellerCertification("En attente")
                .build());
        return customerList;
    }
}
