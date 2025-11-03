/**
 * 
 */
package com.example.api.demo.entity;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote 
 * 
 */
public class AuthenticationRequest {
	
    private String username;
    private String password;
	/**
	 * 
	 */
	public AuthenticationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param username
	 * @param password
	 */
	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
}
