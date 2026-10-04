package com.example.ecommerceProject.Services;

import com.example.ecommerceProject.DTOs.AuthResponseDTO;
import com.example.ecommerceProject.DTOs.ShowUserDTO;
import com.example.ecommerceProject.DTOs.UserAddRequestDTO;
import com.example.ecommerceProject.Entity.User;

public interface UserServices {
//    ShowUserDTO addUser(UserAddRequestDTO user);

    AuthResponseDTO registerNewUser(UserAddRequestDTO user);

    ShowUserDTO showUser(int id);
}
