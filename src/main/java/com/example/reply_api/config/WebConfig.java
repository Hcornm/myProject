package com.example.reply_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/").allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE").maxAge(3000);
    }
}
