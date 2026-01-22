/**
 * 
 */
package com.example.api.demo.service.util;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.api.demo.controller.AuthController;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote Implementation of Redis and Bloom Filter.
 * The Bloom Filter acts like a bouncer outside your 
 * database — a fast, memory-efficient pre-check before 
 * even touching the DB.
 * 
 */
@Service
public class BloomFilterService {
	
	private Logger logger = LogManager.getLogger(BloomFilterService.class);

    private static final int SIZE = 1_000_000;
    private static final int[] SEEDS = {7, 11, 13, 31, 37, 61};

    private final RedisTemplate<String, Object> redisTemplate;
    
    /**
	 * @param redisTemplate
	 */
	public BloomFilterService(RedisTemplate<String, Object> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}

	private final String key = "username_bloom_filter";

    /**
     * Adds a username to the Bloom filter.
     */
    public void addUserName(String username) {
    	logger.info("BloomFilterService:addUserName().execution started...");
        for (int seed : SEEDS) {
            int hash = hash(username, seed);
            redisTemplate.opsForValue().setBit(key, hash, true);
        }
    }

    /**
	 * Checks whether a username already exists.
	 * Returns true if the username might exist, 
	 * and false if it definitely does not.
     */
    public boolean mightContainUserName(String username) {
    	logger.info("BloomFilterService:mightContainUserName().execution started...");
        for (int seed : SEEDS) {
            int hash = hash(username, seed);
            Boolean bit = redisTemplate.opsForValue().getBit(key, hash);
            if (bit == null || !bit) {
                return false;
            }
        }
        return true;
    }

    /**
     * Simple hash function using a seed value.
     */
    private int hash(String username, int seed) {
        int result = 0;
        byte[] bytes = username.getBytes(StandardCharsets.UTF_8);
        for (byte b : bytes) {
            result = seed * result + b;
        }
        return Math.abs(result % SIZE);
    }
}