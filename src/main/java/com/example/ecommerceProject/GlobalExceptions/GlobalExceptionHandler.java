package com.example.ecommerceProject.GlobalExceptions;

import com.example.ecommerceProject.DTOs.ErrorDTO;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.service.annotation.HttpExchange;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException exception , WebRequest request){

        ErrorDTO errorDTO = new ErrorDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>( errorDTO ,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String ,String>> handleMethodArgNotValidException(MethodArgumentNotValidException exception){
        Map<String ,String > errors = new HashMap<>();

        for (FieldError error : exception.getBindingResult().getFieldErrors()){
            errors.put(error.getField() , error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(ex.getLocalizedMessage());
    }
}
