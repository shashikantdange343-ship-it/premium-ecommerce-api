package com.example.ecommerceProject.DTOs;

import java.util.List;

public record ShowUserDTO(String name , String email , int age , List<OrdersDTO> orders) {

}
