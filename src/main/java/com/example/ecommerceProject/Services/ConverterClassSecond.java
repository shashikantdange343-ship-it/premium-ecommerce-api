package com.example.ecommerceProject.Services;

import com.example.ecommerceProject.DTOs.ShowUserDTO;
import com.example.ecommerceProject.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConverterClassSecond {

    private final OrderServices orderServices;

    public ShowUserDTO toUserDTO(User user){
        if (user == null )return null ;
        return new ShowUserDTO(user.getUsername(),user.getEmail(),user.getAge() ,orderServices.getOrders(user.getId()));
    }
}
