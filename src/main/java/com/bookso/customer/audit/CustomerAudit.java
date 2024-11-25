package com.bookso.customer.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
/*
* author: santhosh kumar
* description: Customer Audit for Auditing
 */
@Component("customerAudit")
public class CustomerAudit implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.ofNullable("CUSTOMER_MS");
    }
}
