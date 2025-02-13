package com.elengel.api.fullstack.service.auth;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${security-jwt-expiration-in-minutes}")
    private Long TIEMPO_EXPIRACION_MINUTOS;


    @Value("${security-secret-key-signed}")
    private String SECRET_KEY;



    public String generateToken(UserDetails user,Map<String, Object> extraCaclaims) {

        System.out.println("llego a generar el token");
        Date issueAt = new Date(System.currentTimeMillis());
        Date expiration = new Date ((TIEMPO_EXPIRACION_MINUTOS*60*1000)+issueAt.getTime());//24 horas

        String jwt = Jwts.builder()
                .setClaims(extraCaclaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issueAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
        return jwt;

    }

    /*
    public String generateToken(UserDetails user, Map<String, Object> extraClaims) {
        System.out.println("llego a generar el token");

        Date issueAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((TIEMPO_EXPIRACION_MINUTOS * 60 * 1000) + issueAt.getTime());


        if (extraClaims == null) {
            extraClaims = new HashMap<>();  // Correccion de nullpointer
        }

        extraClaims.put("username", user.getUsername());
        extraClaims.remove("previousToken");  //para evitar que el token con cada request se haga massss grandeee

        String jwt = Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issueAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }
*/
    private Key generateKey() {
        byte[] key = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(key);
    }

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody();
    }
}
