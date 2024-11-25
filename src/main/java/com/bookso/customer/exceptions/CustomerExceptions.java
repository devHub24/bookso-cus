package com.bookso.customer.exceptions;

import com.bookso.customer.enums.CustomerErrors;
import lombok.Getter;
import lombok.Setter;

/*
* author: santhosh kumar
* description: CustomerException
 */
@Setter
@Getter
public class CustomerExceptions extends RuntimeException {

    private CustomerErrors customerError;
    private String message;

    public CustomerExceptions(CustomerErrors error,String message) {

        this.customerError=error;
        this.message=message;
    }
}
