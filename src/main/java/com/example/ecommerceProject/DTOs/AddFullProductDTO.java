package com.example.ecommerceProject.DTOs;

import jakarta.validation.Valid;

public record AddFullProductDTO(

        @Valid AddProductRequestDTO product,
        @Valid AddProductDetailsDTO details
) {}

