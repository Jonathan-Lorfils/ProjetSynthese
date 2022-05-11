package com.example.projetnft.service;

import com.example.projetnft.model.Customer;
import com.example.projetnft.model.Orders;
import com.example.projetnft.repository.CustomerRepository;
import com.example.projetnft.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    public OrderRepository orderRepository;

    @Mock
    public CustomerRepository customerRepository;

    @InjectMocks
    public OrderService orderService;

    private Customer customer;

    @BeforeEach
    void setup() {
        customer = Customer.customerBuilder()
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
    public void testGetOrdersByCustomer(){
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(orderRepository.getAllByCustomer(customer)).thenReturn((getListOfOrderss()));
        Optional<List<Orders>> actualOrders = orderService.getOrdersbyCustomer(1);
        assertThat(actualOrders.get()).isEqualTo(getListOfOrderss());
    }

    private List<Orders> getListOfOrderss(){
        List<Orders> orderList = new ArrayList<>();

        orderList.add(Orders.orderBuilder()
                .id(1)
                .customer(null)
                .price(5)
                .build());
        orderList.add(Orders.orderBuilder()
                .id(2)
                .customer(null)
                .price(10)
                .build());
        orderList.add(Orders.orderBuilder()
                .id(3)
                .customer(null)
                .price(15)
                .build());
        return orderList;
    }
}