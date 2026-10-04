package com.example.ecommerceProject.Controller;

import com.example.ecommerceProject.DTOs.AuthRequestLoginDTO;
import com.example.ecommerceProject.DTOs.AuthResponseDTO;
import com.example.ecommerceProject.DTOs.UserAddRequestDTO;
import com.example.ecommerceProject.Repository.UserRepository;
import com.example.ecommerceProject.Security.JwtUtil;
import com.example.ecommerceProject.Services.CustomUserDetailService;
import com.example.ecommerceProject.Services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/User")
public class AuthController {

    private final UserServices userServices;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailService customUserDetailService;

    @PostMapping()
    public ResponseEntity<AuthResponseDTO> regesterNewUser(@RequestBody UserAddRequestDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.registerNewUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginRequest(@RequestBody AuthRequestLoginDTO loginRequest){
        try {
            // 1. AuthenticationManager (Boss) ko bolo ID/Password check kare
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
            );
        } catch (Exception e) {
            // Agar password galat hua toh yahan code aayega
            return ResponseEntity.status(401).body("Error: Galat Email ya Password!");
        }

        final UserDetails userDetails = customUserDetailService.loadUserByUsername(loginRequest.getUserName());

        final String jwtToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok().body(jwtToken);
    }

}
