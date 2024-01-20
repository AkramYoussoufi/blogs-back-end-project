package com.project.blog.service;


import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;

import java.util.Date;

@Service
@AllArgsConstructor
public class JWTService {

    private static final String JWT_PASSWORD = "YourJWTPasswordMustBeLongerThenExpected";
    private static final long EXPIRATION_DATE = 86_400_000; // 1 day
    public String generateToken(Long id){
        return Jwts.builder()
                .setSubject(id.toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .signWith(SignatureAlgorithm.HS512, JWT_PASSWORD)
                .compact();
    };

    public String extractJWT(String token){
      return Jwts.parser()
              .setSigningKey(JWT_PASSWORD)
              .parseClaimsJws(token)
              .getBody()
              .getSubject();
    };

}
