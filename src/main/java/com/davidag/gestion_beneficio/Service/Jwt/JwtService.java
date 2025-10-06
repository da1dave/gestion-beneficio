package com.davidag.gestion_beneficio.Service.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secretBase64;


    private static final long DEFAULT_EXP_MS = 2 * 60 * 60 * 1000L;

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretBase64);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String subject, Map<String, Object> claims) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + DEFAULT_EXP_MS);

        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(now)
                .expiration(exp)
                .signWith(getSignKey())       
                .compact();
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String getStringClaim(String token, String name) {
        Claims c = extractAllClaims(token);
        Object v = c.get(name);
        return v == null ? null : v.toString();
    }

    public boolean isTokenValid(String token) {
        try {
            
            Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public String extractBearer(String authorizationHeader) {
        if (authorizationHeader == null) return null;
        String prefix = "Bearer ";
        return authorizationHeader.startsWith(prefix)
                ? authorizationHeader.substring(prefix.length()).trim()
                : null;
    }
}
