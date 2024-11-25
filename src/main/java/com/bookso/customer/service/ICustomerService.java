package com.bookso.customer.service;

import com.bookso.customer.dto.CustomerDto;
import com.bookso.customer.dto.NewCustomerDto;
import com.bookso.customer.dto.ProfileDto;

import java.util.List;

public interface ICustomerService {

    public boolean createCustomer(NewCustomerDto newCustomer);
    public CustomerDto getCustomerByEmail(String email);
    public CustomerDto getCustomerByMobile(String mobile);
    public boolean updateCustomer(String email, CustomerDto customerDto);
    public void deleteCustomer(String email);
    public ProfileDto getMyProfile(String email);
    public boolean updateSubscription(Long customerId, int subscriptionType, int subscriptionDurationType);
    public List<CustomerDto> getAll();
}
