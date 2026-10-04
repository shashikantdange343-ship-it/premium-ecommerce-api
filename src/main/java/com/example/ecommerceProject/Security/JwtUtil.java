package com.example.ecommerceProject.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Ye tumhara 'Secret Stamp' hai. Isey koi hack nahi kar sakta. (Asli projects me isey application.properties me rakhte hain)
    private static final String SECRET_KEY = "MySuperSecretKeyForPremiumEcommerceAppWhichNeedsToBeVeryLong";

    // 1. JWT Token Generate karna (VIP Pass banana)
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject) // Yahan hum email set kar rahe hain
                .setIssuedAt(new Date(System.currentTimeMillis())) // Pass kab bana
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 ghante baad pass expire
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Secret key aur algorithm se lock karna
                .compact();
    }

    // 2. Token se Username (Email) nikalna
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 3. Token check karna (Valid hai ya nahi)
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // --- Helper Methods (Inko bas machinery samajh lo, jo background me kaam karti hai) ---

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}