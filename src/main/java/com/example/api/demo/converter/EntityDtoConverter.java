/**
 *
 */
package com.example.api.demo.converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.api.demo.dto.TaskDto;
import com.example.api.demo.entity.Task;
import com.example.api.demo.service.TasksService;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote Manual mapping converts entities to DTOs and vice versa. 
 * 
 */
public final class EntityDtoConverter {
	
	Logger logger = LogManager.getLogger(TasksService.class);

	public EntityDtoConverter() {
	}

	public Task toEntity(TaskDto taskDto) {
		logger.info("EntityDtoConverter:toEntity().execution started...");
		Task task = new Task(taskDto.getTaskId(), taskDto.getTaskTitle(), 
				taskDto.getTaskDescription(), taskDto.getTaskStatus());

		return task;
	}

	public TaskDto toDto(Task task) {
		logger.info("EntityDtoConverter:toDto().execution started...");
		TaskDto taskDto = new TaskDto(task.getTaskId(), task.getTaskTitle(), 
				task.getTaskDescription(), task.getTaskStatus());

		return taskDto;
	}

}
