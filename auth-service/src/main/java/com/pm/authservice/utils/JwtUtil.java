package com.pm.authservice.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


@Component
public class JwtUtil {
    private final Key secretKey;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        // Decode the Base64 encoded secret key
        // Decode the secret key from Base64
        // Ensure the secret key is in bytes
        byte[] keyBytes = Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8));
        // Ensure the key length is appropriate for HMAC algorithms
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email,String role) {
        return Jwts.builder()
                .subject(email)
                .claim("role",role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))// Token valid for 24 hours
                .signWith(secretKey)
                .compact();

    }
}
