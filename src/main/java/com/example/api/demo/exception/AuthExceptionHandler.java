/**
 * 
 */
package com.example.api.demo.exception;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote Handles authentication exceptions in the application.
 * 
 */
@Component
public class AuthExceptionHandler implements AuthenticationEntryPoint {
	
	Logger logger = LogManager.getLogger(AuthExceptionHandler.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("AuthExceptionHandler.commence().Unauthorized {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User is not Authenticated");

	}

}
