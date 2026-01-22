/**
 * 
 */
package com.example.api.demo.service;

import com.anbu.mfaserver.exception.UserAlreadyExistException;

/**
 * 
 */
public interface UserService {
	
	com.example.api.demo.entity.User registerUser(com.example.api.demo.entity.User user) throws UserAlreadyExistException;
	int getActiveUserCount();

}
