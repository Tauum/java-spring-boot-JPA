package com.example.javaspringboot.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// followed tutorial: https://www.youtube.com/watch?v=Ly79YDERpas
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer getCorsConfiguration(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**") //allow all default endpoints & children of endpoints
                        .allowedOrigins("*") // allow all origins
                        .allowedMethods("*") // allow all methods
                        .allowedHeaders("*"); // allow all headers
            }
        };
    }

}
