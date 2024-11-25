package com.bookso.customer.utils;

import org.springframework.stereotype.Service;

import java.util.Random;

/*
* author: santhosh kumar
* description: Utility class for CustomerApplication
 */
@Service
public class CustomerUtils {
    /*
    * author: santhosh kumar
    * params: Long
    * returns: Long
    * description: Method to create a random Profile Id
     */
    public Long getProfileId(Long customerId){
        Long result = 100000L+(new Random().nextInt(900000000))+customerId;
        return result;
    }

}
