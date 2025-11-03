/**
 * 
 */
package com.example.api.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.demo.entity.User;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote Repository interface for performing CRUD operations on the Task entity.
 * This interface provides methods like save(), findById(), findAll(), and deleteById() 
 * without requiring explicit implementation.
 * 
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
}
