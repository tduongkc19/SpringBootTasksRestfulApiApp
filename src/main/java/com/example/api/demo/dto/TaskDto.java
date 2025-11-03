/**
 * 
 */
package com.example.api.demo.dto;

/**
 * Date: 10/17/2025
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNoteA Data Transfer Object (DTO) in Spring Boot is a 
 * simple Java object used to transfer data between different 
 * layers of an application—such as the client, service, and 
 * database layers. Its main purpose is to encapsulate and 
 * structure the exchanged data, promoting separation of 
 * concerns, enhancing security, and improving performance.
 */
public class TaskDto {
	

	private Long taskId;
	private String taskTitle = "";
	private String taskDescription = "";
	private String taskStatus = "";

	public TaskDto() {
	}


	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param status
	 */
	public TaskDto(Long id, String title, String description, String status) {
		super();
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





}
