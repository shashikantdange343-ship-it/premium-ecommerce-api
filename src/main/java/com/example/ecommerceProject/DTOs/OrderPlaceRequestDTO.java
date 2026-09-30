package com.example.ecommerceProject.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

public record OrderPlaceRequestDTO(
        int productId,

        @NotBlank(message = "User Name Must ! ")
        String userName
) {}
