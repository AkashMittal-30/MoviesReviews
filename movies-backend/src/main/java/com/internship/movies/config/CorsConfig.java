//package com.internship.movies.config;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/v1/**") // Replace with your API endpoint pattern
//                .allowedOrigins("http://localhost:3000") // Replace with your React app's URL
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("Content-Type")
//                .allowCredentials(true);
//    }
//}

/* @CrossOrigin(origins = "*")  we can instead add this line to the controller classes*/
