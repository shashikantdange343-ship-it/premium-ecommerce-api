package com.example.ecommerceProject.Services;

import com.example.ecommerceProject.DTOs.ShowUserDTO;
import com.example.ecommerceProject.DTOs.UserAddRequestDTO;
import com.example.ecommerceProject.Entity.User;
import com.example.ecommerceProject.GlobalExceptions.ResourceNotFoundException;
import com.example.ecommerceProject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.util.LangUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices{

    private final UserRepository userRepository;
    private final ConverterClass converterClass;
    private final ConverterClassSecond converterClassSecond;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ShowUserDTO addUser(UserAddRequestDTO user) {
       String hashedPassword = passwordEncoder.encode(user.password());
       UserAddRequestDTO newUserAddReq = new UserAddRequestDTO(user.name(),user.email(),hashedPassword,user.age());
       User newUser = converterClass.toUser(newUserAddReq);
       return converterClassSecond.toUserDTO( userRepository.save(newUser));
    }

    public ShowUserDTO showUser(int id){
       Optional<User> user = userRepository.findById(id);
       if (user.isEmpty()){
           throw new ResourceNotFoundException("User Id :" + id + " Not Found In DataBase ");
       }
       User getUser = user.get();
       return converterClassSecond.toUserDTO(getUser);
    }

}
