package com.bookso.customer.mapper;

import com.bookso.customer.dto.CustomerDto;
import com.bookso.customer.dto.NewCustomerDto;
import com.bookso.customer.dto.ProfileDto;
import com.bookso.customer.entity.Customer;
import com.bookso.customer.entity.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
* author: santhosh kumar
* description: Mapper class for Customer and Profile
 */
@Service
@Slf4j
public class CustomerMapper {

    /*
    * author: Santhosh Kumar
    * param: CustomerDto
    * return: Customer
    */
    public Customer toCustomer(CustomerDto customerDto){

        return Customer.builder()
                .name(customerDto.getName())
                .contact(customerDto.getContact())
                .email(customerDto.getEmail())
                .subscriptionType(customerDto.getSubscriptionType())
                .build();
    }

    /*
    * author: santhosh kumar
    * params: Customer
    * returns: CustomerDto
    */
    public static CustomerDto toCustomerDto(Customer customer){

        return CustomerDto.builder()
                .name(customer.getName())
                .contact(customer.getContact())
                .email(customer.getEmail())
                .subscriptionType(customer.getSubscriptionType())
                .build();
    }

    /*
    * author: santhosh kumar
    * params: ProfileDto
    * returns: Profile
    */
    public Profile toProfile(ProfileDto profileDto){

        return Profile.builder()
                .customerId(profileDto.getCustomerId())
                .subscriptionType(profileDto.getSubscriptionType())
                .subscriptionDuration(profileDto.getSubscriptionDuration())
                .subscribedOn(profileDto.getSubscribedOn())
                .validTill(profileDto.getValidTill())
                .build();
    }

    /*
     * author: santhosh kumar
     * params: Profile
     * returns: ProfileDto
     */
    public ProfileDto toProfileDto(Profile profile){

        return ProfileDto.builder()
                .customerId(profile.getCustomerId())
                .subscriptionType(profile.getSubscriptionType())
                .subscriptionDuration(profile.getSubscriptionDuration())
                .subscribedOn(profile.getSubscribedOn())
                .validTill(profile.getValidTill())
                .build();
    }

    /*
    * author: santhosh kumar
    * params: NewCustomerDto
    * returns: CustomerDto
    * description: Mapper method to Change NewCustomerDto to Customer
     */
    public Customer newCustomerMapper(NewCustomerDto newCustomerDto){

        return Customer.builder()
                .name(newCustomerDto.getName())
                .email(newCustomerDto.getEmail())
                .contact(newCustomerDto.getContact())
                .build();
    }


}
