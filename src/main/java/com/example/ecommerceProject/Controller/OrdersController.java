package com.example.ecommerceProject.Controller;

import com.example.ecommerceProject.DTOs.OrderPlaceRequestDTO;
//import com.example.ecommerceProject.DTOs.OrderResponseDTO;
import com.example.ecommerceProject.Services.OrderServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/Order")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderServices orderServices;

    @PostMapping("/Order")
    public ResponseEntity<String> placeOrder(@RequestBody OrderPlaceRequestDTO order){
        if (orderServices.placeOrder(order)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Order Placed !");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Out Of Stock !");
    }

}
