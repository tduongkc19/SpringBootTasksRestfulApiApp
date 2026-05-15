/**
 * 
 */
package com.example.api.demo.service.util;

import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.example.api.demo.service.impl.TasksServiceImpl;
import com.example.api.demo.service.impl.UserServiceMfaImpl;

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
	
	private final UserServiceMfaImpl userService;
    private final TasksServiceImpl tasksService;
    private final Environment environment;
	
	// Example
    public CustomStatsEndpoint(UserServiceMfaImpl userService, TasksServiceImpl tasksService, Environment environment) {
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
