package com.bookso.customer.controller;

import com.bookso.customer.constants.CustomerConstants;
import com.bookso.customer.dto.*;
import com.bookso.customer.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookso/v1")
@Tag(name = "Customer REST APIs", description = "Customer MS Rest API endpoints")
public class CustomerController {

    private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,})$";
    private static final String mobileRegex = "^[0-9]{10}$";
    @Autowired
    ICustomerService customerService;

    //Documentation
    @Operation(summary = "Create Customer API", description = "REST API to create Customer")
    @ApiResponses({
            @ApiResponse(responseCode = CustomerConstants.STATUS_200,
                    content = @Content(schema = @Schema(implementation = BaseResponseDto.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @PostMapping("/customers")
    public ResponseEntity<BaseResponseDto> createCustomer(@Valid @RequestBody NewCustomerDto newCustomer){
     customerService.createCustomer(newCustomer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseDto(CustomerConstants.STATUS_201,CustomerConstants.CUSTOMER_CREATED_SUCCESSFULLY));
    }

    //Documentation
    @Operation(summary = "Fetch Customer API", description = "REST API to Fetch Customer by Email")
    @ApiResponses({
            @ApiResponse(responseCode = CustomerConstants.STATUS_200,
                    content = @Content(schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping("email/{email}/customers")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@Valid @PathVariable("email")
                                                              @Pattern(regexp = emailRegex, message = "Invalid Mail id")
                                                              String email){
        CustomerDto customer = customerService.getCustomerByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    //Documentation
    @Operation(summary = "Fetch Customer API", description = "REST API to Fetch Customer by Contact")
    @ApiResponses({
            @ApiResponse(responseCode = CustomerConstants.STATUS_200,
                    content = @Content(schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping("contact/{contact}/customers")
    public ResponseEntity<CustomerDto> getCustomerByContact(@Valid @PathVariable("contact")
                                                                @Pattern(regexp = mobileRegex, message = "Invalid Mobile number")
                                                            String contact){
        CustomerDto customerDto = customerService.getCustomerByMobile(contact);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);

    }

    //Documentation
    @Operation(summary = "Update Customer API", description = "REST API to Update Customer")
    @ApiResponses({
            @ApiResponse(responseCode = CustomerConstants.STATUS_200,
                    content = @Content(schema = @Schema(implementation = BaseResponseDto.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @PutMapping("customers/{email}")
    public ResponseEntity<BaseResponseDto> updateCustomer(@PathVariable ("email") String email,
                                                      @Valid @RequestBody CustomerDto customerDto){
        customerService.updateCustomer(email, customerDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseDto(CustomerConstants.STATUS_200, CustomerConstants.CUSTOMER_UPDATED_SUCCESSFULLY));
    }

    //Documentation
    @Operation(summary = "Delete Customer API", description = "REST API to Delete Customer by Email")
    @ApiResponses({
            @ApiResponse(responseCode = CustomerConstants.STATUS_200,
                    content = @Content(schema = @Schema(implementation = BaseResponseDto.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @DeleteMapping("/customers/{email}")
    public ResponseEntity<BaseResponseDto> deleteCustomer(@PathVariable ("email") String email){
        customerService.deleteCustomer(email);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseDto(CustomerConstants.STATUS_201, CustomerConstants.CUSTOMER_DELETED_SUCCESSFULLY));
    }

    //Documentation
    @Operation(summary = "Fetch Profile API", description = "REST API to Fetch Profile by Email")
    @ApiResponses({
            @ApiResponse(responseCode = CustomerConstants.STATUS_200,
                    content = @Content(schema = @Schema(implementation = ProfileDto.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping("/customers/{email}/profile")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable("email") String email){
        ProfileDto profileDto = customerService.getMyProfile(email);
        return ResponseEntity.status(HttpStatus.OK).body(profileDto);
    }

    //Documentation
    @Operation(summary = "Update Subscription API", description = "REST API to Update Subscription")
    @ApiResponses({
            @ApiResponse(responseCode = CustomerConstants.STATUS_200,
                    content = @Content(schema = @Schema(implementation = BaseResponseDto.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @PutMapping("/customers/{customerId}/{subscriptionType}/{subscriptionDurationType}/profile")
    public ResponseEntity<BaseResponseDto> updateSubscription(@PathVariable("customerId") Long customerId,
                                                          @PathVariable("subscriptionType") int subscriptionType,
                                                          @PathVariable("subscriptionDurationType") int subscriptionDurationType){
        customerService.updateSubscription(customerId,subscriptionType,subscriptionDurationType);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseDto(CustomerConstants.STATUS_200, CustomerConstants.PROFILE_UPDATED_SUCCESSFULLY));
    }


    //Documentation
    @Operation(summary = "Fetch Customers API", description = "REST API to Fetch Customers")
    @ApiResponses({
            @ApiResponse(responseCode = CustomerConstants.STATUS_200,
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAll());
    }

}
