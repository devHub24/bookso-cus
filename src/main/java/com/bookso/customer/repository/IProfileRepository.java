package com.bookso.customer.repository;

import com.bookso.customer.entity.Customer;
import com.bookso.customer.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
* author: santhosh kumar
* description: Profile Repository
 */
@Repository
public interface IProfileRepository extends JpaRepository<Profile,Long> {

    public Profile findByCustomerId(Long customerId);

}
