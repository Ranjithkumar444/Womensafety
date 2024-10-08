package com.womensafety.Womensafety.config;

import com.womensafety.Womensafety.Service.CustomUserDetailsService;
import com.womensafety.Womensafety.Service.JWTservice;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTservice jwTservice;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYW5qaXRoIiwiaWF0IjoxNzI4Mzg5MzIyLCJleHAiOjE3Mjg0MjUzMjJ9.I69c6Oi9-bYP5ra7otFmiAo0Q0o4XCAuq3OWER4uHVw

        String authheader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(authheader != null && authheader.startsWith("Bearer ")){
            token = authheader.substring(7);
            username = jwTservice.extractUserName(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = (UserDetails) context.getBean(CustomUserDetailsService.class).loadUserByUsername(username);

            if(jwTservice.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
