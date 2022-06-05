package com.example.javaspringboot.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authManager;
    public CustomAuthenticationFilter authManagerFilter;

    @Override // THIS OVERRIDES THE DEFAULT SPRING SECURITY IMPLEMENTATION
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        var test = request.getRequestURI();
        var test2 = request.getHeaderNames();
        var test3 = request.getRequestURL();
        var test4 = request.getSession();
        var test5 = request.getUserPrincipal();
        var test6 = request.getParameterNames();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
        // ^^^^^^^^^^^ shows granted authorities empty
        return authManager.authenticate(authToken);
    }

    @Override // THIS OVERRIDES THE DEFAULT SPRING SECURITY IMPLEMENTATION
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        // THIS IS USING SPRING SECURITY BUILT IN USER
        User springUserDetails = (User) authentication.getPrincipal();
        // THIS NEEDS MAKING SECURE AND ENCRYPTED vvvvvvv
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); // THIS IS USING AUTH0 DEPENDENCY

        String access_token = JWT.create()
                .withSubject(springUserDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 120 * 60 * 1000)) // this should be 2 hours
                .withIssuer(request.getRequestURI().toString())
                .withClaim("roles", springUserDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(springUserDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 120 * 60 * 1000)) // this should be 2 hours
                .withIssuer(request.getRequestURI().toString())
                .withClaim("roles", springUserDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    //https://www.youtube.com/watch?v=her_7pa0vrg
//    @Override // DO SOMETHING WITH THIS TO PREVENT BRUTE FORCE ATTACKS WITH LIMITED NUMBER OF ATTEMPTS IN A TIME-FRAME
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        super.unsuccessfulAuthentication(request, response, failed);
//    }
}
