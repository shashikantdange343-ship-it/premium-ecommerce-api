package com.example.ecommerceProject.Services;

import com.example.ecommerceProject.Entity.User;
import com.example.ecommerceProject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Bouncer bola: "Bhai ye lo email, check karo VIP list me hai ya nahi?"
        // Toh hum apne database me email se user dhoond rahe hain
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.get();

        if (user == null) {
            System.out.println("Bouncer ko user nahi mila!");
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        // 2. Agar mil gaya, toh apne 'User' ko Spring Security ke bhasha 'UserDetails' me translate karke wapas bhej do
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .age(user.getAge())
//                .roles("USER") // Abhi ke liye hum sabko normal "USER" maan rahe hain
                .build();
    }
}
