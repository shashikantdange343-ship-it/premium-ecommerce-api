package com.example.ecommerceProject.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AddProductRequestDTO(
        @NotBlank(message = "Name Cannot Be Blank !")
        String name ,

        @Min(value = 10 ,message = "Price Must Be Above 10 Rupees ! ")
        int price ,

        @Min(value = 1 , message = "1 Quantity Minimum !")
        int quantity ) {
}
