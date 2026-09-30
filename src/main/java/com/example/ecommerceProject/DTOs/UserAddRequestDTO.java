package com.example.ecommerceProject.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserAddRequestDTO(

        @NotBlank(message = "Name Should Not Be Blank")
        String name,

        @NotBlank(message = "Email Should Not Be Blank")
        @Email(message = "Email Format Error! (e.g : rahul123@gmail.com)")
        String email,

        @NotBlank
        @Size(min = 6 , message = "Minimum 6 Characters")
        String password,

        @Min(value = 18 , message = "Age Is Not Valid Below 18!")
        int age

) {}
