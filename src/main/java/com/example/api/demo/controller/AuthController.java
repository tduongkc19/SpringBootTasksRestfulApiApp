package com.example.api.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.demo.entity.AuthenticationRequest;
import com.example.api.demo.entity.User;
import com.example.api.demo.repository.UserRepository;
import com.example.api.demo.service.impl.CustomUserDetailsServiceImpl;
import com.example.api.demo.service.util.JwtUtil;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote 
 * 
 */
@RestController
@RequestMapping("/api/v1")
public class AuthController {
	
	// Injecting the required dependencies for authentication and user management.
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /** 
     * Constructor dependency Injection for the required components.
	 * @param authenticationManager
	 * @param userDetailsService
	 * @param userRepository
	 * @param passwordEncoder
	 * @param jwtUtil
	 */
	public AuthController(AuthenticationManager authenticationManager, CustomUserDetailsServiceImpl userDetailsService,
			UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/register")
    public String registerUser(@Validated  @RequestBody User user) {
        // Encode the user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the user to the database
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        return jwtUtil.generateToken(userDetailsService.loadUserByUsername(authenticationRequest.getUsername()));
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
    
    
    
    
    
}