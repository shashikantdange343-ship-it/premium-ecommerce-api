package com.example.ecommerceProject.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AddProductDetailsDTO(

        @NotBlank(message = "Product Description Must !")
        String description,

        @NotBlank(message = "Product Brand Must !")
        String brand ,

        @NotBlank(message = "Product Category Must !")
        String category ,

        @NotBlank(message = "Product Color Must !")
        String color ,

        @NotBlank(message = "Product Material Must !")
        String material ,

        Double weight ,

        String warranty) {
}
