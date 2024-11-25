package com.bookso.customer.repository;

import com.bookso.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
* author: santhosh kumar
* description: Customer Repository class
 */
@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {

    public Customer findByEmail(String email);
    public Customer findByContact(String contact);
    public void deleteByEmail(String email);
}


