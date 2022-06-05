package com.example.javaspringboot.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService uds; // THIS IS BUILT INTO SPRING SECURITY AND NEEDED TO VERIFY LOGIN ATTEMPTS

//    private final BCryptPasswordEncoder bcpe; // THIS IS ENCODING PASSWORDS (I THINK ITS BUILT INTO SPRING)
    private final PasswordEncoder bcpe; // THIS IS ENCODING PASSWORDS (I THINK ITS BUILT INTO SPRING)

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uds).passwordEncoder(bcpe);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http); // THIS IS THE DEFAULT AND USES COOKIES LOOK MORE INTO THIS
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers(GET, "/**").hasAnyAuthority("STUDENT"); // MODERATE ENDPOINT ACCESS HERE
//        http.authorizeRequests().antMatchers(GET,"/User/**").hasAnyAuthority("UNDEFINED") // MODERATE ENDPOINT ACCESS HERE
        http.authorizeRequests().anyRequest().authenticated();
//        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class); // INTERCEPT EVERY REQUEST BEFORE ANY OTHER FILTERS
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{ // NO FUCKING IDEA WHAT THIS DOES
        return super.authenticationManagerBean();
    }

}
