package com.example.projetnft.service;

import com.example.projetnft.model.Customer;
import com.example.projetnft.model.Orders;
import com.example.projetnft.repository.CustomerRepository;
import com.example.projetnft.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public Optional<List<Orders>> getOrdersbyCustomer(Integer customerId) {
        try {
            Customer customer = customerRepository.findById(customerId).get();
            List<Orders> customerOrders = orderRepository.getAllByCustomer(customer);
            return Optional.of(customerOrders);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
