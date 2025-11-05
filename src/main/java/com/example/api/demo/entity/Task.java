/**
 * 
 */
package com.example.api.demo.entity;


import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote This class is mapped to a table in a database.
 * 
 */
@Entity
@Table(name = "tasks")
@Data
public class Task implements Serializable {
	
    @Serial
    private static final long serialVersionUID = 1L;
	
	// Primary key with auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;
    
    // Title name field with validation
    @NotEmpty(message = "Task title is required.")
    @Size(min = 10, message = "Title name should have at least 10 characters.")
	private String taskTitle = "";
	
    // Task description field with validation
    @NotEmpty(message = "Task description is required.")
    @Size(min = 100, message = "Description should have at least 100 characters.")
	private String taskDescription = "";
	
    // Task status field with default 'Pending'.
	private String taskStatus = "";
	
	
	public Task() {
	}

	public Task(Long id, String title, String description, String status) {
		this.taskId = id;
		this.taskTitle = title;
		this.taskDescription = description;
		this.taskStatus = status;
	}
	

	/**
	 * @return the taskId
	 */
	public Long getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the taskTitle
	 */
	public String getTaskTitle() {
		return taskTitle;
	}

	/**
	 * @param taskTitle the taskTitle to set
	 */
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	/**
	 * @return the taskDescription
	 */
	public String getTaskDescription() {
		return taskDescription;
	}

	/**
	 * @param taskDescription the taskDescription to set
	 */
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	/**
	 * @return the taskStatus
	 */
	public String getTaskStatus() {
		return taskStatus;
	}

	/**
	 * @param taskStatus the taskStatus to set
	 */
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskTitle=" + taskTitle + ", taskDescription=" + taskDescription
				+ ", taskStatus=" + taskStatus + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(taskDescription, taskId, taskStatus, taskTitle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(taskDescription, other.taskDescription) && Objects.equals(taskId, other.taskId)
				&& Objects.equals(taskStatus, other.taskStatus) && Objects.equals(taskTitle, other.taskTitle);
	}
	
	
	
}
