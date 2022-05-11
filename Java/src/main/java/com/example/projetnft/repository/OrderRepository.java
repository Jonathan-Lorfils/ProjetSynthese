package com.example.projetnft.repository;

import com.example.projetnft.model.Customer;
import com.example.projetnft.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    List<Orders> getAllByCustomer(Customer customer);
}