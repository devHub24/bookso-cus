package com.bookso.customer.service;

import com.bookso.customer.dto.CustomerDto;
import com.bookso.customer.dto.NewCustomerDto;
import com.bookso.customer.entity.Customer;
import com.bookso.customer.entity.Profile;
import com.bookso.customer.exceptions.CustomerExceptions;
import com.bookso.customer.repository.ICustomerRepository;
import com.bookso.customer.repository.IProfileRepository;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ImpCustomerServiceTest {

    @Mock
    ICustomerRepository customerRepository;


    @Autowired
    @InjectMocks
    ImpCustomerService customerService;

    @Test
    public void testCreateCustomer(){
        NewCustomerDto newCustomerDto = new NewCustomerDto("sk","sk@gmail.com","9998899988");
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setName("sk");
        customer.setEmail("sk@gmail.com");
        customer.setContact("9999999999");
        customer.setSubscriptionType("BASIC");

        //when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        assertTrue(customerService.createCustomer(newCustomerDto));

    }

    @Test
    public void testCreateCustomerFailure(){
        NewCustomerDto newCustomerDto = new NewCustomerDto("sk","sk@gmail.com","9998899988");
        when(customerRepository.save(any(Customer.class))).thenThrow(DataIntegrityViolationException.class);
        assertThrows(CustomerExceptions.class, ()->{
            customerService.createCustomer(newCustomerDto);
        });
    }

    @Test
    public void testCreateCustomerDataAccessFailure(){
        NewCustomerDto newCustomerDto = new NewCustomerDto("sk","sk@gmail.com","9998899988");
        when(customerRepository.save(any(Customer.class))).thenThrow(new DataAccessException("Error") {});
        assertThrows(CustomerExceptions.class, ()->{
            customerService.createCustomer(newCustomerDto);
        });
    }

    @Test
    public void testGetCustomerByEmail(){
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setName("sk");
        customer.setEmail("sk@gmail.com");
        customer.setContact("9999999999");
        customer.setSubscriptionType("BASIC");

        when(customerRepository.findByEmail(anyString())).thenReturn(customer);
        assertEquals(customer.getEmail(), customerService.getCustomerByEmail("sk@gmail.com").getEmail());

    }

    @Test
    public void testGetCustomerByEmailFailure(){
        when(customerRepository.findByEmail(anyString())).thenThrow(NoResultException.class);
        assertThrows(CustomerExceptions.class, () ->{
            customerService.getCustomerByEmail("sk@gmail.com");
        });
    }

    @Test
    public void testGetCustomerByEmailDataAccessFailure(){
        when(customerRepository.findByEmail(anyString())).thenThrow(new DataAccessException("Error") {});
        assertThrows(CustomerExceptions.class, () -> {
            customerService.getCustomerByEmail("sk@gmail.com");
        });
    }

    @Test
    public void testGetCustomerByContact(){
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setName("sk");
        customer.setEmail("sk@gmail.com");
        customer.setContact("9999999999");
        customer.setSubscriptionType("BASIC");

        when(customerRepository.findByContact(anyString())).thenReturn(customer);
        assertEquals("9999999999", customerService.getCustomerByMobile("9999999999").getContact());
    }

    @Test
    public void testGetCustomerByContactFailure(){

        when(customerRepository.findByContact(anyString())).thenReturn(null);
        assertThrows(CustomerExceptions.class, ()-> {
            customerService.getCustomerByMobile("9999999999");
        });
    }

    @Test
    public void testGetCustomerByContactDataAccessFailure(){
        when(customerRepository.findByContact(anyString())).thenThrow(new DataAccessException("Error"){});
        assertThrows(CustomerExceptions.class,() ->{
            customerService.getCustomerByMobile("9999999999");
        });
    }

    @Test
    public void testUpdateCustomer(){
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setName("sk");
        customer.setEmail("sk@gmail.com");
        customer.setContact("9999999999");
        customer.setSubscriptionType("BASIC");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("sk");
        customerDto.setEmail("sk@gmail.com");
        customerDto.setContact("9999999999");
        customerDto.setSubscriptionType("BASIC");

        when(customerRepository.findByEmail(anyString())).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        assertTrue(customerService.updateCustomer("sk@gmail.com",customerDto));
    }

    @Test
    public void testUpdateCustomerFailure(){
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setName("sk");
        customer.setEmail("sk@gmail.com");
        customer.setContact("9999999999");
        customer.setSubscriptionType("BASIC");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("sk");
        customerDto.setEmail("sk@gmail.com");
        customerDto.setContact("9999999999");
        customerDto.setSubscriptionType("BASIC");

        when(customerRepository.findByEmail(anyString())).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenThrow(new DataAccessException("Error"){});
        assertThrows(CustomerExceptions.class, () -> {
            customerService.updateCustomer("sk@gmail.com", customerDto);
        });
    }

   /* @Test
    public void testGetProfile(){
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setName("sk");
        customer.setEmail("sk@gmail.com");
        customer.setContact("9999999999");
        customer.setSubscriptionType("BASIC");

        String email = "sk@gmail.com";

        Profile profile = new Profile(
                234L,
                123L,
                "BASIC",
                "BASIC",
                LocalDate.now(), LocalDate.now());

        when(customerRepository.findByEmail(anyString())).thenReturn(customer);
        when(profileRepository.findByCustomerId(anyLong())).thenReturn(Optional.ofNullable(profile));

        assertEquals(profile.getCustomerId(), customerService.getMyProfile("sk@gmail.com").getCustomerId());
    }*/


}
