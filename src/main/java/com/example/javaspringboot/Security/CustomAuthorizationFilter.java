package com.example.javaspringboot.Security;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// AT THIS POINT THE USER IS ALREADY AUTHENTICATD THEY JUST NEED TO BE SET IN THE AUTHENTICATION CONTEXT
public class CustomAuthorizationFilter extends OncePerRequestFilter { // INTERCEPTS EVERY REQUEST
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/login")){ filterChain.doFilter(request,response); } // DO NOTHING IF LOGGING IN
        else{
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try {
                    String token = authorizationHeader.substring("Bearer ".length()); // TAKES TOKEN STRING AND REMOVES BEARER
                    // THIS NEEDS MAKING SECURE AND ENCRYPTED vvvvvvv
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); // <<<<<<<<<<<<<<<<<<<<<<<
                    JWTVerifier verifier = JWT.require(algorithm).build(); // USING AUTH0
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String email = decodedJWT.getSubject(); // GETS EMAIL
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class); // GETS ROLES
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(role -> {  authorities.add(new SimpleGrantedAuthority(role)); }); // CONVERTS ALL USERS ROLE INTO AN AUTHORITY
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, null); // PASSWORD IS NULL AT THIS POINT
                    SecurityContextHolder.getContext().setAuthentication(authToken); // INSERTS TOKEN INTO CONTEXT // THIS SHOWS AUTHENTICATED FALSE, DETIALS FALSE AND GRANTED AUTHORITIES EMPTY
                    filterChain.doFilter(request, response);
                }

                catch (Exception e){
                    response.setHeader("error" , e.getMessage() );
                    response.setStatus(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
//                    response.sendError(FORBIDDEN.value()); // GIVE 403 YOU DONT HAVE ACCESS

                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }
            else{ filterChain.doFilter(request, response); }
        }
    }
}
