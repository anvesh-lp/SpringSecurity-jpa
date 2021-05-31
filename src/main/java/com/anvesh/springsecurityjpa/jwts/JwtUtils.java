package com.anvesh.springsecurityjpa.jwts;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtUtils {

    private final String SECRET_KEY = "SecretKeySecretKeySecretKeySecretKeySecr" +
            "etKeySecretKeySecretKeySecretKeySecretKeySecret" +
            "KeySecretKeySecretKeySecretKeySecretKeySecretKey";

    //    TODO validate the already existing  tokens
//    Todo check attempts
//    Todo add jwt filters for authorization
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

}
