/**
 * 
 */
package com.example.api.demo.service.util;

import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.example.api.demo.service.TasksService;
import com.example.api.demo.service.UserServiceImpl;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote
 * 
 * 
 */
@Component
@Endpoint(id = "customStats")
public class CustomStatsEndpoint {
	
	private final UserServiceImpl userService;
    private final TasksService tasksService;
    private final Environment environment;
	
	// Example
    public CustomStatsEndpoint(UserServiceImpl userService, TasksService tasksService, Environment environment) {
        this.userService = userService;
        this.tasksService = tasksService;
        this.environment = environment;
    }

    // Example
    @ReadOperation
    public Map<String, Object> customStats() {
        return Map.of(
            "activeUsers", userService.getActiveUserCount(),
            "systemMode", environment.getProperty("spring.profiles.active", "default")
        );
    }

}
