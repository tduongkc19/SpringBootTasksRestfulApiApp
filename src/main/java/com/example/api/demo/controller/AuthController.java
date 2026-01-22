package com.example.api.demo.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anbu.mfaserver.exception.UserAlreadyExistException;
import com.example.api.demo.entity.AuthenticationRequest;
import com.example.api.demo.entity.User;
import com.example.api.demo.repository.UserRepository;
import com.example.api.demo.service.CustomUserDetailsService;
import com.example.api.demo.service.UserService;
import com.example.api.demo.service.util.JwtUtil;

import dev.samstevens.totp.exceptions.QrGenerationException;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote An authentication controller class that handles 
 * user registration, login, and authorization for API access.
 * 
 */
@RestController
@RequestMapping("/")
public class AuthController {
	
	private Logger logger = LogManager.getLogger(AuthController.class);
	
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    
    /**
	 * @param authenticationManager
	 * @param userDetailsService
	 * @param userRepository
	 * @param passwordEncoder
	 * @param jwtUtil
	 */
	public AuthController(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService,
			UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/register")
    public String registerUser(@Validated  @RequestBody User user) {
		logger.info("AuthController:registerUser().execution started...");
        // Encode the user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the user to the database
        try {
			userService.registerUser(user);
		} catch (UserAlreadyExistException e) {
			e.printStackTrace();
			logger.info("AuthController:registerUser().exception.catch..." + e);
		}
        
        logger.info("AuthController:registerUser().User registered successfully.");
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    	logger.info("AuthController:loginUser().execution started...");
    	authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }

    @GetMapping("/hello")
    public String helloWorld() {
    	logger.info("AuthController:hello().execution started...");
        return "Hello, World!";
    }
    
    
    
    
    
}
