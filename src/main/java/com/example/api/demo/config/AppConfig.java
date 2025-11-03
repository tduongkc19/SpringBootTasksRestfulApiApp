/**
 * 
 */
package com.example.api.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.api.demo.converter.EntityDtoConverter;
import com.example.api.demo.service.util.JwtUtil;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote The AppConfig class is used to instantiate beans 
 * in the Spring Boot application.
 * 
 */
@Configuration
public class AppConfig {
   @Bean
   public EntityDtoConverter getEntityDtoConverter() {
       return new EntityDtoConverter();
   }
   
   @Bean
   public JwtUtil getJwtUtil() {
       return new JwtUtil();
   }
   
   
}
