package com.example.api.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote Main class to run the Spring Boot application.
 * 
 */
@SpringBootApplication
@EnableCaching // Enabling caching globally
public class TasksRestfulApi  {

	public static void main(String[] args) {
		SpringApplication.run(TasksRestfulApi.class, args);
	}

}
