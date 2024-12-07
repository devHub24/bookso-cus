package com.bookso.customer.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "customer")
@Data
public class CustomerInfo {

    private String message;
    private Map<String, String> contactInfo;
}
