package com.bookso.customer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
* author: santhosh kumar
* description: Customer Entity
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String contact;
    private String subscriptionType;

}
