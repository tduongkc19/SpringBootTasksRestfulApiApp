/**
 * 
 */
package com.example.api.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.api.demo.entity.Task;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote Repository interface for performing CRUD operations on the Task entity.
 * This interface provides methods like save(), findById(), findAll(), and deleteById() 
 * without requiring explicit implementation.
 * 
 */
@Repository
public interface TasksRepository extends JpaRepository<Task, Long> {
	// Additional query methods can be defined here if needed
    @Query("SELECT t FROM Task t WHERE LOWER(t.taskTitle) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Task> findByTitleContainingIgnoreCase(@Param("title") String title);
    
    @Query("SELECT t FROM Task t WHERE LOWER(t.taskTitle) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Task> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    
    
    
}
