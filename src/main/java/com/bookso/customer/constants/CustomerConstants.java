package com.bookso.customer.constants;

import lombok.NoArgsConstructor;

import java.util.Map;

/*
* author: santhosh kumar
* description: Constants for Customer MS
 */
@NoArgsConstructor
public class CustomerConstants {


    public static final String PREMIUM="PREMIUM";
    public static final String GOLD="GOLD";
    public static final String DIAMOND="DIAMOND";
    public static final String BASIC="BASIC";
    public static final String MONTHLY="MONTHLY";
    public static final String YEARLY="YEARLY";

    //Constants for Error Messages
    public static final String CUSTOMER_ALREADY_EXISTS = "Customer already exists with give mailId: %s or given Mobile no: %s";
    public static final String CUSTOMER_NOT_FOUND_BY_MAIL = "Customer not Found with given email id: %s";
    public static final String CUSTOMER_NOT_FOUND_BY_CONTACT = "Customer not Found with given Contact: %s";
    public static final String CUSTOMER_CREATED_SUCCESSFULLY = "Customer Created Successfully";
    public static final String CUSTOMER_UPDATED_SUCCESSFULLY = "Customer updated successfully";
    public static final String CUSTOMER_DELETED_SUCCESSFULLY = "Customer deleted successfully";
    public static final String PROFILE_NOT_FOUND = "Profile not found ofr the customerId: %s";
    public static final String PROFILE_UPDATED_SUCCESSFULLY = "Profile updated successfully";

    //ResponseConstants
    public static final String STATUS_200 = "200";
    public static final String STATUS_201 = "201";
    public static final String STATUS_400 = "400";
    public static final String STATUS_500 = "500";

    //Subscription type map
    public static final Map<Integer, String> SUBSCRIPTION_TYPE_MAP = Map.ofEntries(Map.entry(0,BASIC),
            Map.entry(1, PREMIUM),
            Map.entry(2,GOLD),
            Map.entry(3,DIAMOND));
    //Subscription duration Map
    public static final Map<Integer, String> SUBSCRIPTION_DURATION_MAP = Map.ofEntries(Map.entry(1,MONTHLY), Map.entry(2,YEARLY));
//Subscription duration mapper
    public static final Map<Integer, Integer> SUBSCRIPTION_DURATION_DAYS = Map.ofEntries(Map.entry(1,30),Map.entry(2,365));


}

