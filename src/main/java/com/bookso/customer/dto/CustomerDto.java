package com.bookso.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* author: santhosh kumar
* description: CustomerDto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//Documentation
@Schema(name = "CustomerDto", description = "Schema for Customer Dto")
@Builder
public class CustomerDto {

    @Schema(description = "Name of the Customer", example = "Santhosh")//Documentation
    private String name;

    @Schema(description = "Email of the Customer", example = "santhosh@gmail.com")//Documentation
    @Email(message = "Invalid Email")
    private String email;

    @Schema(description = "Phone number of the Customer", example = "9999999999")//Documentation
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
    private String contact;

    @Schema(description = "Subscription type of Customer(By default BASIC)", examples = {"BASIC", "PREMIUM", "GOLD"})//Documentation
    private String subscriptionType;

}
