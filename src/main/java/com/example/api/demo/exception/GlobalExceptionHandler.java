/**
 * 
 */
package com.example.api.demo.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ValidationException;


/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote Handles global exceptions in the application.
 * 
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
		logger.error("GlobalExceptionHandler.handleResourceNotFoundException().ResourceNotFoundException: ",
				ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
    	logger.info("GlobalExceptionHandler.handleUserNotFound().User not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponseImpl("USER_NOT_FOUND", "User not found"));
    }
    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(ValidationException ex) {
    	logger.warn("GlobalExceptionHandler.handleValidation().Validation failed: {}", ex.getMessage());
        return ResponseEntity.badRequest()
            .body(new ErrorResponseImpl("VALIDATION_ERROR", ex.getMessage()));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
    	logger.error("handleValidation.handleGeneral().Unexpected error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponseImpl("INTERNAL_ERROR", "Something went wrong"));
    }

}
