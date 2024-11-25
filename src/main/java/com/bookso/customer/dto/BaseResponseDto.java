package com.bookso.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Schema(name = "BaseResponseDto", description = "Schema for Base Response Dto")
public class BaseResponseDto {

    @Schema(name = "Code", description = "Code of the Response", example = "200")
    private String code;

    @Schema(name = "Message", description = "Message of the response", example = "Boo Created Successfully")
    private String message;
}
