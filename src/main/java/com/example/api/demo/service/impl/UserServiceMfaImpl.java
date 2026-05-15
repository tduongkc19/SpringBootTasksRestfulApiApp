/**
 * 
 */
package com.example.api.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.api.demo.entity.MfaTokenData;
import com.example.api.demo.entity.User;
import com.example.api.demo.entity.UserMfa;
import com.example.api.demo.exception.InvalidTokenException;
import com.example.api.demo.exception.UserAlreadyExistException;
import com.example.api.demo.repository.UserRepository;
import com.example.api.demo.service.UserService;

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
public class UserServiceMfaImpl implements UserService{
	
	private final UserRepository userRepository;
	
	
	/**
	 * @param userRepository
	 */
	public UserServiceMfaImpl(UserRepository userRepository) {
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

	@Override
	public MfaTokenData registerUser(UserMfa user) throws UserAlreadyExistException, QrGenerationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verifyTotp(String code, String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendRegistrationConfirmationEmail(UserMfa user) throws MessagingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyUser(String token) throws InvalidTokenException {
		// TODO Auto-generated method stub
		return false;
	}
	

}
