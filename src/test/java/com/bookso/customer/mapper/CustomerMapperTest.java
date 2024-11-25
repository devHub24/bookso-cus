package com.bookso.customer.mapper;

import com.bookso.customer.dto.CustomerDto;
import com.bookso.customer.dto.NewCustomerDto;
import com.bookso.customer.dto.ProfileDto;
import com.bookso.customer.entity.Customer;
import com.bookso.customer.entity.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void testToCustomer(){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("sk");
        customerDto.setEmail("sk@gmail.com");
        customerDto.setContact("9999999999");
        customerDto.setSubscriptionType("BASIC");

        assertEquals("sk",customerMapper.toCustomer(customerDto).getName());
    }

    @Test
    public void testToCustomerDto(){
        Customer customer = new Customer();
        customer.setName("sk");
        customer.setEmail("sk@gmail.com");
        customer.setContact("9999999999");
        customer.setSubscriptionType("BASIC");

        assertEquals("sk",customerMapper.toCustomerDto(customer).getName());
    }

    @Test
    public void testToProfile(){
        ProfileDto profileDto = new ProfileDto(
                2340L,
                "BASIC",
                "BASIC",
                LocalDate.now(),
                LocalDate.now());

        assertEquals(2340L,customerMapper.toProfile(profileDto).getCustomerId());
    }

    @Test
    public void testToProfileDto(){
        Profile profile = new Profile(
                1234L,
                2340L,
                "BASIC",
                "BASIC",
                LocalDate.now(),
                LocalDate.now());

        assertEquals(2340L,customerMapper.toProfileDto(profile).getCustomerId());
    }

    @Test
    public void testNewCustomerMapper(){
        NewCustomerDto newCustomerDto = new NewCustomerDto("sk","sk@gmail.com","9998899988");
        assertEquals("sk@gmail.com",customerMapper.newCustomerMapper(newCustomerDto).getEmail());

    }
}
