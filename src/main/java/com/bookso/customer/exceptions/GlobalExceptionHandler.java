package com.bookso.customer.exceptions;

import com.bookso.customer.dto.ErrorDto;
import com.bookso.customer.enums.CustomerErrors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.bookso.customer.enums.CustomerErrors.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bookso.customer.enums.CustomerErrors.*;

/*
* author: santhosh kumar
* description: GlobalExceptionHandler
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /*
    * author: santhosh kumar
    * params: MethodArgumentException
    * returns: ErrorDto
    * description: Method to handle MethodArgumentException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidMethodArguments(MethodArgumentNotValidException me){
        List<FieldError> fieldErrorsList = me.getBindingResult().getFieldErrors();

        Map<String, String> errors = fieldErrorsList
                .stream()
                .collect(Collectors.toMap(FieldError::getField, err -> err.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /*
    * author: santhosh kumar
    * params: CustomerException
    * returns ErrorDto
    * description: Method to handle any CustomerException
     */
    @ExceptionHandler(CustomerExceptions.class)
    public ResponseEntity<ErrorDto> handleCustomerExceptions(CustomerExceptions customerExceptions, WebRequest webRequest){

     ErrorDto error = new ErrorDto(
             HttpStatus.BAD_REQUEST,
             customerExceptions.getMessage(),
             webRequest.getDescription(false),
             LocalDateTime.now());
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
