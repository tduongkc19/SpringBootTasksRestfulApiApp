/**
 * 
 */
package com.example.api.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.demo.dto.TaskDto;
import com.example.api.demo.entity.Task;
import com.example.api.demo.service.impl.TasksServiceImpl;
import com.google.common.base.Preconditions;

import jakarta.validation.Valid;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote The TasksController class handles HTTP requests 
 * and returns appropriate responses.
 * 
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/tasks")
public class TasksController {
	
	private Logger logger = LogManager.getLogger(TasksController.class);
	
	private final String defaultTaskStatus = "Pending";
	
	@Autowired
	private TasksServiceImpl tasksService;
	
	// Get tasks in pages.
    @GetMapping("/task-pages")
    @CrossOrigin(origins = "http://localhost:4200")
	public Page<TaskDto> getPaginatedTasks(Pageable pageable) {
    	logger.info("TasksController:getPaginatedTasks().execution started...");
		return tasksService.getPaginatedTasks(pageable);
	}
    
    
	// Search title with pages.
    @GetMapping("/search-pages")
	public Page<TaskDto> searchPaginatedTasks(@RequestParam String title, Pageable pageable) {
    	logger.info("TasksController:searchPaginatedTasks().execution started...");
		return tasksService.searchPaginatedTasks(title, pageable);
	}
    
    // Search task by title
    @GetMapping("/search")
    public List<Task> searchTasksByTitle(@RequestParam String title) {
    	logger.info("TasksController:searchTasksByTitle().execution started...");
        return tasksService.searchByTitle(title);
    }
    
	// Create a new multiple tasks
	@PostMapping("/bulk")
	public ResponseEntity<List<Task>> createAllTasks(@Valid @RequestBody List<Task> tasks) {
		logger.info("TasksController:createAllTasks().execution started...");
	    List<Task> newTasks = tasks.stream()
	            .peek(task -> {
	                if (StringUtils.isEmpty(task.getTaskStatus())) {
	                    task.setTaskStatus(defaultTaskStatus);
	                }
	            })
	            .toList();

	        return ResponseEntity.ok(tasksService.createAllTasks(newTasks));
	}

	
    // Create a new single task
    @SuppressWarnings("deprecation")
	@PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
    	logger.info("TasksController:createTask().execution started...");
    	if(StringUtils.isEmpty(task.getTaskStatus())) {
    		task.setTaskStatus(defaultTaskStatus);
    	}
    	Task createdTask = tasksService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
    
    
    // Retrieve all tasks
    @GetMapping
    public List<Task> getAllTasks() {
    	logger.info("TasksController:getAllTasks().execution started...");
        return tasksService.getAllTasks();
    }
    
    
    // Retrieve task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
    	logger.info("TasksController:getTaskById().execution started...");
    	Task task = tasksService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
    
    
    // Update single task details
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
    	logger.info("TasksController:updateTask().execution started...");
    	Task updatedTask = tasksService.updateTaskById(id, task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
    
    // Update bulk tasks details
    @PutMapping("/bulk-update")
    public ResponseEntity<List<Task>> bulkUpdateTasks(@RequestBody List<Task> tasks) {
    	logger.info("TasksController:bulkUpdateTasks().execution started...");
    	// Call service to update tasks by IDs
        List<Task> updatedTasks = tasksService.bulkUpdateTasks(tasks);
        return ResponseEntity.ok(updatedTasks);
    }
    
    
    // Single delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
    	logger.info("TasksController:deleteTask().execution started...");
    	// Call service to delete items by ID
    	tasksService.deleteTask(id);
        return ResponseEntity.ok("{\"message\" : \"Item deleted successfully\"}");
    }
    
	// Bulk delete multiple tasks
    @DeleteMapping("/bulk-delete")
    public ResponseEntity<String> deleteTasks(@RequestBody List<Long> ids) {
    	logger.info("TasksController:deleteTasks().execution started...");
        // Call service to delete tasks by IDs
    	tasksService.deleteAllTasks(ids);
        return ResponseEntity.ok("Items deleted successfully");
    }
    
    
}
