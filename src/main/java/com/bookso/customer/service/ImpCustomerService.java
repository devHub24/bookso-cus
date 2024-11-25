package com.bookso.customer.service;

import com.bookso.customer.constants.CustomerConstants;
import com.bookso.customer.dto.CustomerDto;
import com.bookso.customer.dto.NewCustomerDto;
import com.bookso.customer.dto.ProfileDto;
import com.bookso.customer.entity.Customer;
import com.bookso.customer.entity.Profile;
import com.bookso.customer.enums.CustomerErrors;
import com.bookso.customer.exceptions.CustomerExceptions;
import com.bookso.customer.mapper.CustomerMapper;
import com.bookso.customer.repository.ICustomerRepository;
import com.bookso.customer.repository.IProfileRepository;
import com.bookso.customer.utils.CustomerUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/*
* author: santhosh kumar
* description: Implementation of ICustomerService
 */
@Slf4j
@Service
public class ImpCustomerService implements ICustomerService{


  @Autowired
  ICustomerRepository customerRepository;
  @Autowired
  IProfileRepository profileRepository;
  @Autowired
  CustomerUtils customerUtils;
  @Autowired
  CustomerMapper customerMapper;

    /*
    * @author: santhosh kumar
    * @params: NewCustomerDto
    * @returns: Boolean
    * @description: Method to create a new Customer
    * @throws: CustomerException
     */
    @Override
    public boolean createCustomer(NewCustomerDto newCustomer) {
        if(Objects.isNull(newCustomer)){
            log.error("New CustomerDto is Null");
            return false;
        }

        try {
            Customer customer = customerMapper.newCustomerMapper(newCustomer);
            customer.setSubscriptionType(CustomerConstants.BASIC);
            Customer savedCustomer = customerRepository.save(customer);
            log.info("Created customer:{}",savedCustomer);

            Profile profile = createProfile(savedCustomer);
            log.info("Profile created successfully:{}",profile);
            return true;

        }catch(ConstraintViolationException | DataIntegrityViolationException dataIntegrityViolationException) {
            log.error("Customer Already exists cause: {}", dataIntegrityViolationException.getMessage());

            throw new CustomerExceptions(CustomerErrors.CUSTOMER_ALREADY_EXISTS,
                    String.format(CustomerConstants.CUSTOMER_ALREADY_EXISTS,newCustomer.getEmail(),newCustomer.getContact()));

        }catch(DataAccessException dataAccessException){
            log.error("Error while creating customer, cause: {}", dataAccessException.getMessage());
            throw new CustomerExceptions(CustomerErrors.GENERAL_ERROR, "Error while creating customer");
        }
    }

    /*
    * @author: santhosh kumar
    * @params: Email of Customer
    * @returns: return Customer details
     */
    @Override
    public CustomerDto getCustomerByEmail(String email) {
        try {
            Customer customer = customerRepository.findByEmail(email);
            if(Objects.isNull(customer)){
                log.error("Customer not found");
                throw new CustomerExceptions(
                        CustomerErrors.CUSTOMER_NOT_FOUND,
                        String.format(CustomerConstants.CUSTOMER_NOT_FOUND_BY_MAIL,email));
            }

            CustomerDto customerDto = customerMapper.toCustomerDto(customer);
            log.info("Customer obtained successfully");
            return customerDto;

        }catch(EmptyResultDataAccessException n){

            log.error("Customer not found, cause:{}", n.getMessage());
            throw new CustomerExceptions(
                    CustomerErrors.CUSTOMER_NOT_FOUND,
                    String.format(CustomerConstants.CUSTOMER_NOT_FOUND_BY_MAIL,email));

        }catch(DataAccessException dataAccessException){

            log.error("Error while fetching Customer, cause:{}", dataAccessException.getMessage());
            throw new CustomerExceptions(CustomerErrors.GENERAL_ERROR,
                    String.format("Error while fetching customer with email: %s",email));
        }
    }

    /*
    * @author: santhosh kumar
    * @params: Mobile no of the customer
    * @returns: CustomerDto for the given mobile number
    * @description: Getting Customer details based on the mobile number
     */
    @Override
    public CustomerDto getCustomerByMobile(String mobile) {
        try {
            Customer customer = customerRepository.findByContact(mobile);
            if(Objects.isNull(customer)){
                log.error("Customer not found");
                throw new CustomerExceptions(CustomerErrors.CUSTOMER_NOT_FOUND,
                        String.format(CustomerConstants.CUSTOMER_NOT_FOUND_BY_CONTACT,mobile));

            }

            CustomerDto customerDto = customerMapper.toCustomerDto(customer);
            log.info("Customer obtained Successfully");

            return customerDto;

        }catch(EmptyResultDataAccessException n){
            log.error("Customer not found, cause:{}", n.getMessage());
            throw new CustomerExceptions(CustomerErrors.CUSTOMER_NOT_FOUND,
                    String.format(CustomerConstants.CUSTOMER_NOT_FOUND_BY_CONTACT,mobile));

        }catch(DataAccessException d){
            log.error("Error while fetching Customer, cause:{}", d.getMessage());
            throw new CustomerExceptions(CustomerErrors.GENERAL_ERROR,
                    String.format("Error while fetching customer with mobile number: %s",mobile));
        }

    }

    /*
    * @author: santhosh kumar
    * @params: Customer Email and Customer Dto
    * @returns: true or false based on update
    * @description: Updating customer
     */
    @Override
    public boolean updateCustomer(String email, CustomerDto customerDto) {

        if(Objects.isNull(customerDto)){
            log.error("CustomerDto is null");
            return false;
        }

        try {
            Customer customer = customerRepository.findByEmail(email);
            if(Objects.isNull(customer)){
                log.error("Customer not found");
                throw new CustomerExceptions(
                        CustomerErrors.CUSTOMER_NOT_FOUND,
                        String.format(CustomerConstants.CUSTOMER_NOT_FOUND_BY_MAIL,email));
            }

            customer.setName(customerDto.getName());
            customer.setContact(customerDto.getContact());
            customer.setEmail(customerDto.getEmail());

            customerRepository.save(customer);

            return true;
        }catch(NoSuchElementException n){
            log.error("Customer not found, cause:{}", n.getMessage());
            throw new CustomerExceptions(CustomerErrors.CUSTOMER_NOT_FOUND,
                    String.format(CustomerConstants.CUSTOMER_NOT_FOUND_BY_MAIL,email));

        }catch(DataAccessException d){
            log.error("Error while updating customer, cause:{}", d.getMessage());
            throw new CustomerExceptions(CustomerErrors.GENERAL_ERROR, "Error while updating the customer");
        }
    }
    /*
     * @author: santhosh kumar
     * @params: email of the customer
     * @description: deleting a customer by email id
     */
    @Transactional
    @Override
    public void deleteCustomer(String email) {
        try {
            customerRepository.deleteByEmail(email);
        }catch(EmptyResultDataAccessException e){
            log.error("Customer not found, cause:{}", e.getMessage());
            throw new  CustomerExceptions(CustomerErrors.CUSTOMER_NOT_FOUND,
                    String.format(CustomerConstants.CUSTOMER_NOT_FOUND_BY_MAIL, email));

        }catch(DataAccessException d){
            log.error("Error while deleting Customer ,cause: {}", d.getMessage());
            throw new CustomerExceptions(CustomerErrors.GENERAL_ERROR, "Error while deleting customer");
        }
    }

    /*
    * @author: santhosh kumar
    * @params: Email of Customer
    * @returns: Profile of the customer
    * @description: Getting profile of a customer by Email
     */
    @Override
    public ProfileDto getMyProfile(String email) {

        if(StringUtils.isEmpty(email)) {
            log.error("Invalid Email");
            throw new CustomerExceptions(CustomerErrors.GENERAL_ERROR, "Email can't be null");
        }

        try {
            Customer customer = customerRepository.findByEmail(email);
            if(Objects.isNull(customer)){
                log.error("Customer not found for email id:{}",email);
                throw new CustomerExceptions(CustomerErrors.CUSTOMER_NOT_FOUND,
                        String.format(CustomerConstants.CUSTOMER_NOT_FOUND_BY_MAIL, email));
            }

            Profile profile = profileRepository.findByCustomerId(customer.getCustomerId());
            ProfileDto profileDto = customerMapper.toProfileDto(profile);
            return profileDto;

        }catch(EmptyResultDataAccessException e){
            log.error("Customer not found for email id:{}",email);
            throw new CustomerExceptions(CustomerErrors.CUSTOMER_NOT_FOUND,
                    String.format(CustomerConstants.CUSTOMER_NOT_FOUND_BY_MAIL, email));

        }catch(DataAccessException d){
            log.error("Error while fetching Profile");
        }
        return null;
    }

    @Override
    public boolean updateSubscription(Long customerId, int subscriptionType, int subscriptionDurationType) {
        try{
            Profile profile = profileRepository.findByCustomerId(customerId);
            if(Objects.isNull(profile)){
                log.error("Profile not found");
                throw new CustomerExceptions(CustomerErrors.PROFILE_NOT_FOUND,
                        String.format(CustomerConstants.PROFILE_NOT_FOUND,customerId));

            }
            profile.setSubscriptionType(CustomerConstants.SUBSCRIPTION_TYPE_MAP.get(subscriptionType));
            profile.setSubscriptionDuration(CustomerConstants.SUBSCRIPTION_DURATION_MAP.get(subscriptionDurationType));
            profile.setSubscribedOn(LocalDate.now());
            profile.setValidTill(profile.getSubscribedOn().plusDays(CustomerConstants.SUBSCRIPTION_DURATION_DAYS.get(subscriptionDurationType)));
            profileRepository.save(profile);
            Customer customer = customerRepository.findById(customerId).get();
            customer.setSubscriptionType(CustomerConstants.SUBSCRIPTION_TYPE_MAP.get(subscriptionType));
            customerRepository.save(customer);
            return true;
        }catch(EmptyResultDataAccessException e){
            log.error("Profile not found");
            throw new CustomerExceptions(CustomerErrors.PROFILE_NOT_FOUND,
                    String.format(CustomerConstants.PROFILE_NOT_FOUND,customerId));

        }catch(DataAccessException d){
            log.error("Error while updating subscription");
            throw new CustomerExceptions(CustomerErrors.GENERAL_ERROR, "Error while updating Subscription");

        }

    }

    /*
    * @author: santhosh kumar
    * @returns: List of Customer
    * @description: Method to get a list of Customers
     */
    @Override
    public List<CustomerDto> getAll() {

        try{
            List<Customer> customerList = customerRepository.findAll();
            return customerList.stream().map(CustomerMapper::toCustomerDto).toList();
        }catch (EmptyResultDataAccessException e){
            log.error("Empty result List");
            throw new CustomerExceptions(CustomerErrors.GENERAL_ERROR, "No customer found");
        }catch(DataAccessException d){
            log.error("Error while fetching customers");
            throw new CustomerExceptions(CustomerErrors.GENERAL_ERROR, "Error while fetching customers");

        }

    }

    /*
    * author: santhosh kumar
    * params: Customer
    * returns: Profile
    * description: Method to create Profile
     */
    public Profile createProfile(Customer customer) throws DataIntegrityViolationException{
            log.info("Creating Profile for the customer:{}",customer);
            Profile profile = new Profile();
            profile.setCustomerId(customer.getCustomerId());
            profile.setProfileId(customerUtils.getProfileId(customer.getCustomerId()));
            profile.setSubscriptionType(customer.getSubscriptionType());
            profile.setSubscriptionDuration(CustomerConstants.BASIC);

        return profileRepository.save(profile);
    }


}
