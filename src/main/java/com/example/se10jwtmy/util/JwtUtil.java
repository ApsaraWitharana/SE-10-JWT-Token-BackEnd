package com.example.se10jwtmy.util;

import com.example.se10jwtmy.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@PropertySource(ignoreResourceNotFound = true,value = "classpath:otherprops.properties")
public class JwtUtil implements Serializable {

    private static final long serialVersionUID = 234234523523L;

    public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 12;

    @Value("${jwt.secret}")
    private String secretKey;

    // Retrieve username from jwt token

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }

    public Claims getUserRoleCodeFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
    // Retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver){
        final Claims claims =getAllClaimFromToken(token);
        return claimResolver.apply(claims);
    }
    //for retrieving any information from token
    //we will need the secret key

    private Claims getAllClaimFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new java.util.Date());

    }
    //generate token for user

    public String generateToken(UserDTO userDTO){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",userDTO.getRole());
        return doGenerateToken(claims,userDTO.getEmail());
    }

    //while creating the token
    //1.define claims of the token,like Issuer ,expirate
    //2.sign the jwt using the HS512 algorithm and secrete

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date (System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512,secretKey).compact();

    }
    //validate token
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
