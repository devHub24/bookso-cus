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

    private String code;
    private String message;
}
