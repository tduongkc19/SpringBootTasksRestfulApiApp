/**
 * 
 */
package com.example.api.demo.service;

import org.apache.catalina.User;

import com.example.api.demo.entity.EmailConfirmationToken;

import jakarta.mail.MessagingException;

/**
 * 
 */
public interface EmailService {
	 void sendConfirmationEmail(EmailConfirmationToken emailConfirmationToken) throws MessagingException;

	 void sendWelcomeEmail(com.example.api.demo.entity.User savedUser);

	 void sendWelcomeEmail(User savedUser);
}
