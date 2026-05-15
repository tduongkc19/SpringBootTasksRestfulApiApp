/**
 * 
 */
package com.example.api.demo.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.api.demo.service.util.BloomFilterService;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote An advanced data-structure implementation in a 
 * Spring Boot Java application, used to quickly determine 
 * whether a record—such as an email or username—already 
 * exists in a system containing billions of data entries.
 * 
 */
@RestController
@RequestMapping("/usernames")
public class UsernameController {
	
	private Logger logger = LogManager.getLogger(UsernameController.class);

   // Injecting the BloomFilterService dependency to manage the Bloom filter operations.
    private BloomFilterService bloomFilterService;
    

	/**
	 * Setter injection for the BloomFilterService dependency.
	 * @param bloomFilterService the bloomFilterService to set
	 */
    @Autowired
	public void setBloomFilterService(BloomFilterService bloomFilterService) {
		this.bloomFilterService = bloomFilterService;
	}

	/**
     * Adds a username to the Bloom filter.
     * Example: POST /usernames/add/john123
     */
    @PostMapping("/add/{username}")
    public String addUsername(@PathVariable String username) {
        bloomFilterService.addUserName(username);
        return " Username added: " + username;
    }

    /**
     * Checks if a username might exist in the Bloom filter.
     * Example: GET /usernames/check/john123
     */
    @GetMapping("/check/{username}")
    public String checkUsername(@PathVariable String username) {
        boolean exists = bloomFilterService.mightContainUserName(username);
        return exists
                ? " Username might exist!"
                : " Username definitely does not exist!";
    }
}
