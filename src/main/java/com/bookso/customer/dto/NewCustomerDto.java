package com.bookso.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* @author: santhosh kumar
* @description: New Customer Dto to create a new Customer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//Documentation
@Schema(name = "NewCustomerDto", description = "New Customer Dto Schema")
@Builder
public class NewCustomerDto {

    @Schema(description = "Name of the Customer", example="Santhosh")//Documentation
    private String name;

    @Schema(description = "Email of the Customer", example="santhosh@gmail.com")//Documentation
    @Email(message = "Invalid Email id")
    private String email;

    @Schema(description = "Mobile number of the Customer", example="9999999999")//Documentation
    @Pattern(regexp = "\\d{10}" ,message = "Invalid mobile number")
    private String contact;
}
