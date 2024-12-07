package com.bookso.customer;

import com.bookso.customer.dto.CustomerInfo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "customerAudit")
@OpenAPIDefinition(info = @Info(title = "Customer MS",
description = "Customer MS API documentation",
contact = @Contact(name = "Santhosh Kumar", email="santhoshsk@gmail.com"),
license = @License(name = "Apache 2.0")))
@EnableConfigurationProperties(CustomerInfo.class)
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
