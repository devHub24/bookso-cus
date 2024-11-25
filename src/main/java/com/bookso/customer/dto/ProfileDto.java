package com.bookso.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
* author: santhosh kumar
* description: ProfileDto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//Documentation
@Schema(name = "ProfileDto", description = "Schema for ProfileDto")
@Builder
public class ProfileDto {

    @Schema(description = "Customer Id", example = "Database Generated Id")//Documentation
    private Long customerId;

    @Schema(description = "Subscription Type of the Profile", examples = {"BASIC", "PREMIUM", "GOLD"})//Documentation
    private String subscriptionType;

    @Schema(description = "Subscription Duration of the Profile", examples = {"MONTHLY", "YEARLY"})//Documentation
    private String subscriptionDuration;

    @Schema(description = "Subscribed date of the Profile", example = "12/09/2024")//Documentation
    private LocalDate subscribedOn;

    @Schema(description = "Subscription Validity Date", example = "12/09/2025")//Documentation
    private LocalDate validTill;
}
