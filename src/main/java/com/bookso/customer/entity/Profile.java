package com.bookso.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/*
* author: santhosh kumar
* description: Profile Entity
 */
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class Profile extends BaseEntity{

    @Id
    private Long profileId;
    private Long customerId;
    private String subscriptionType;
    private String subscriptionDuration;
    private LocalDate subscribedOn;
    private LocalDate validTill;
}
