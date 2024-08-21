package com.example.se10jwtmy.config;
//07
import com.example.se10jwtmy.service.impl.UserServiceImpl;
import com.example.se10jwtmy.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//one req eken ekkt asscess krl ==ek ek req ek filter krl chek krnwa ==// util eke genarate krnwa
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl userService;

    @Value("${jwt.secret}")
    private String secretKey;
    //doFilterInternal() ==allready define wen ekk--servlete eken enne http req awam ek filter krnwa //token ekk ekk en ekk d kiyl
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader("Authorization"); //01 palaweniyt alagnnwa heder ek
        String token = null;
        String email = null;

        if (null != authorization && authorization.startsWith("Bearer ")){ // Bearer+ passe ekk ekkd kiyl
            token = authorization.substring(7); //toke eke palaweni value 7
            email = jwtUtil.getUsernameFromToken(token); //user kwd kiyl
            Claims claims = jwtUtil.getUserRoleCodeFromToken(token); //role ek blnwa
            httpServletRequest.setAttribute("email",email); //set email
            httpServletRequest.setAttribute("role",claims.get("role")); //set role
        }

        if (null != email && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userService.loadUserByUsername(email);

            if (jwtUtil.validateToken(token,userDetails)){ //token ek valid krnwa
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,
                                userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private Claims getClaimsFromJwtToken(String token){
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
    }
}
