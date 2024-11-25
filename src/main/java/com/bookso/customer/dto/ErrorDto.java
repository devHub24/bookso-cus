package com.bookso.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

/*
* author: santhosh kumar
* description: ErrorDto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//Documentation
@Schema(name = "ErrorDto", description = "Schema for Error Dto")
@SuperBuilder
public class ErrorDto extends BaseResponseDto{

    @Schema(description = "HttpStatus Code for the Schema", example = "200")//Documentation
    private HttpStatus code;

    @Schema(description = "Message of the Error", example = "Created Successfully")//Documentation
    private String message;

    @Schema(description = "Path of the Error", example = "uri/bookso/v1/customers/")//Documentation
    private String path;

    @Schema(description = "TimeStamp of the Error", example = "System generated Time")//Documentation
    private LocalDateTime timeStamp;
}
