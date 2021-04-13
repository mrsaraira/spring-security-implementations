package com.mrsaraira.springsecurityvariants.security;

import com.mrsaraira.springsecurityvariants.exceptions.JwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration.period:60000}")
    private int expirationPeriod; // milliseconds

    @Autowired
    private UserDetailsService userDetailsService;


    @PostConstruct
    protected void init() {
        Assert.hasText(header, "Property JWT header cannot be blank");
        Assert.hasText(header, "Property JWT secret key cannot be blank");
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    public String createToken(String userName, String role) {
        Assert.hasText(userName, "Username cannot be blank");
        Assert.hasText(role, "Role cannot be blank");

        Claims claims = Jwts.claims().setSubject(userName);
        claims.put("role", role);
        Date currentTime = new Date();
        Date expirationTime = new Date(currentTime.getTime() + expirationPeriod);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentTime)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("Token is expired or invalid");
        }
    }


    public Authentication getAuthentication(String token) {
        Assert.hasText(token, "Token cannot be blank");
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities());
    }


    public String getUserName(String token) {
        Assert.hasText(token, "Token cannot be blank");
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }


    public String resolveToken(HttpServletRequest request) {
        Assert.notNull(request, "HTTP request cannot be null");
        return request.getHeader(header);
    }

}
