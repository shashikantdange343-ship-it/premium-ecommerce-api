package com.example.ecommerceProject.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ShowProductsDTO(

        int productId,

        String name,

        int price ,

        int quantity ) {
}
