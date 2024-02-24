package com.example.fraudcore.Dao;

import com.example.fraudcore.Domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    public Customer getCustomerById(int id);

}
