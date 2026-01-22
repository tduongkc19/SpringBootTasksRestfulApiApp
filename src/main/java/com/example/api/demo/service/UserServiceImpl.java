/**
 * 
 */
package com.example.api.demo.service;

import org.springframework.stereotype.Service;

import com.anbu.mfaserver.exception.InvalidTokenException;
import com.anbu.mfaserver.exception.UserAlreadyExistException;
import com.anbu.mfaserver.model.MfaTokenData;
import com.example.api.demo.entity.User;
import com.example.api.demo.repository.UserRepository;

import dev.samstevens.totp.exceptions.QrGenerationException;
import jakarta.mail.MessagingException;

/**
 *  @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote
 * 
 */
@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	
	/**
	 * @param userRepository
	 */
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public int getActiveUserCount() {
        // Replace with actual logic
        return 120;
    }

	
	public User registerUser(User user) {
		return userRepository.save(user);
	}
	

}
