/**
 * 
 */
package com.example.api.demo.service;

import com.example.api.demo.entity.MfaTokenData;
import com.example.api.demo.entity.UserMfa;
import com.example.api.demo.exception.InvalidTokenException;
import com.example.api.demo.exception.UserAlreadyExistException;

import dev.samstevens.totp.exceptions.QrGenerationException;
import jakarta.mail.MessagingException;


/** *  @author Tommy Duong
 * 	
 */
public interface UserService {
	
	int getActiveUserCount();
    MfaTokenData registerUser(UserMfa user) throws UserAlreadyExistException, QrGenerationException;
    //MfaTokenData mfaSetup(String email) throws UnkownIdentifierException, QrGenerationException;
    boolean verifyTotp(final String code,String username);
    void sendRegistrationConfirmationEmail(final UserMfa user) throws MessagingException;
    boolean verifyUser(final String token) throws InvalidTokenException;

}
