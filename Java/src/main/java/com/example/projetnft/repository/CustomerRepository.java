package com.example.projetnft.repository;

import com.example.projetnft.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByUsernameAndPassword(String username, String password);

    Customer findByPhoneNumber(String phoneNumber);
}
