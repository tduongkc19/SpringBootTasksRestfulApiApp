/**
 * 
 */
package com.example.api.demo.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote Handles global exceptions in the application.
 * 
 */
public class GlobalExceptionHandler {
	
	Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
		logger.error("GlobalExceptionHandler.handleResourceNotFoundException().ResourceNotFoundException: ",
				ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

}
