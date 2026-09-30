package com.example.ecommerceProject.DTOs;

import java.time.LocalDateTime;

public record ErrorDTO(LocalDateTime timestamp,String message , String details , int status) {

}
