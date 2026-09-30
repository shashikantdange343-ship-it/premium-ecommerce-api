package com.example.ecommerceProject.Controller;

import com.example.ecommerceProject.DTOs.ShowUserDTO;
import com.example.ecommerceProject.DTOs.UserAddRequestDTO;
import com.example.ecommerceProject.Services.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserServices userServices;

    @PostMapping("/User")
    public ResponseEntity<ShowUserDTO> addUserRequest(@Valid @RequestBody UserAddRequestDTO user){
       return ResponseEntity.ok(userServices.addUser(user));
    }

    @GetMapping("/User/{id}")
    public ResponseEntity<ShowUserDTO> showUser(@PathVariable int id){
       return ResponseEntity.ok( userServices.showUser(id));
    }


}
