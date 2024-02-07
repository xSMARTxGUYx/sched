package com.performances.sched.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.performances.sched.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    Customer getCustome(String username, String password);
}