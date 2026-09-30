package com.example.ecommerceProject.Services;

import com.example.ecommerceProject.DTOs.OrderPlaceRequestDTO;
import com.example.ecommerceProject.DTOs.OrdersDTO;

import java.util.List;
//import com.example.ecommerceProject.DTOs.OrderResponseDTO;

public interface OrderServices {
    boolean placeOrder(OrderPlaceRequestDTO order);
    List<OrdersDTO> getOrders(int id);
}
