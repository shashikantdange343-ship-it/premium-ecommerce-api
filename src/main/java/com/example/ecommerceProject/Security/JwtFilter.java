package com.example.ecommerceProject.Security;

import com.example.ecommerceProject.Services.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Request ke Header se 'Authorization' naam ka VIP pass mango
        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwtToken = null;

        // 2. Check karo ki pass bheja hai aur wo "Bearer " se shuru hota hai
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7); // "Bearer " ke baad ka asli token nikal lo
            email = jwtUtil.extractUsername(jwtToken); // Token me se email nikal lo
        }

        // 3. Agar token me email hai, par Bouncer ne abhi tak darwaza nahi khola hai (Context null hai)
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.customUserDetailService.loadUserByUsername(email);

            // 4. Token check karo ki expiry date theek hai na aur email match ho raha hai na
            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                // 5. Sab theek hai, toh Bouncer ko bolo isko andar aane de
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 6. Request ko aage badhne do (chahye andar allow kiya ho ya block)
        filterChain.doFilter(request, response);
    }
}