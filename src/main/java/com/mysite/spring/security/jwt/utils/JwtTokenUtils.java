package com.mysite.spring.security.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    // метод, позволяющий из пользователя сформировать токен
    public String generateToken(UserDetails userDetails) {
        // формирование payload'а
        Map<String, Object> claims = new HashMap<>();

        // добавление списка ролей в токен
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // в payload'е сформируется поле: "roles" : "список_ролей_в_виде_строки"
        claims.put("roles", rolesList);

        // у токена должна быть информация о том, когда он создан и когда истечет время его жизни
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) // subject - тема токена. Как правило, это имя пользователя
                .setIssuedAt(issuedDate) // время выпуска
                .setExpiration(expiredDate) // время, когда токен будет недействительным
                .signWith(SignatureAlgorithm.HS256, secret) // подпись
                .compact();
    }


    // разбор приходящего токена на куски

    // получение имени пользователя из токена
    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    // получение ролей пользователя из токена
    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    // получение Claims (полезные данные) из токена
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
