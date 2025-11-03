/**
 * 
 */
package com.example.api.demo.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import okhttp3.*;
import java.io.IOException;

import com.example.api.demo.converter.EntityDtoConverter;
import com.example.api.demo.converter.ISourceTargetMapper;
import com.example.api.demo.dto.TaskDto;
import com.example.api.demo.entity.Task;
import com.example.api.demo.exception.ResourceNotFoundException;
import com.example.api.demo.repository.TasksRepository;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote Encapsulates the business logic. Service class 
 * for business logic related to Tasks entity.
 * 
 * 
 */
@Service
public class TasksService implements ISourceTargetMapper, IHttpClientService{
	
	Logger logger = LogManager.getLogger(TasksService.class);
	
	private final TasksRepository tasksRepository;
	//private final EntityDtoConverter entityDtoConverter;
	
	
	/**
	 * @param tasksRepository
	 * @param entityDtoConverter
	 */
	public TasksService(TasksRepository tasksRepository, EntityDtoConverter entityDtoConverter) {
		super();
		this.tasksRepository = tasksRepository;
		//this.entityDtoConverter = entityDtoConverter;
	}


    
    // Pagination: Retrieve tasks in pages.
	public Page<TaskDto> getPaginatedTasks(Pageable pageable) {
		logger.info("TasksService:getPaginatedTasks().execution started...");
		Page<Task> tasks = tasksRepository.findAll(pageable);
		Page<TaskDto> paginatedTasks = tasks.map(entity -> {
			//TaskDto taskDto = entityDtoConverter.toDto(entity);
			TaskDto taskDto = toDto(entity);
			return taskDto;
		});

		return paginatedTasks;
	}
	
	// Create a new multiple tasks
    @Transactional
    public List<Task> createAllTasks(List<Task> tasks) {
    	logger.info("TasksService:createAllTasks().execution started...");
        return tasksRepository.saveAll(tasks);
    }

	// Create a new single task
    public Task createTask(Task task) {
    	logger.info("TasksService:createTask().execution started...");
        return tasksRepository.save(task);
    }

    // Retrieve all tasks
    public List<Task> getAllTasks() {
    	logger.info("TasksService:getAllTasks().execution started...");
        return tasksRepository.findAll();
    }

    // Retrieve task by ID
   @Cacheable(value = "task", key = "#id")
    public Task getTaskById(Long id) {
    	logger.info("TasksService:getTaskById().execution started...id:" + id);
    	logger.info("TasksService:getTaskById().Fetching task from database...");
        return tasksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tasks", "id", id));
    }
   

   // Search task by title
   @Cacheable(value = "task", key = "#title")
   public List<Task> searchByTitle(String title) {
   	logger.info("TasksService:searchByTitle().execution started...title:" + title);
   	logger.info("TasksService:searchByTitle().Fetching task from database...");
       return tasksRepository.findByTitleContainingIgnoreCase(title);
   }
   
   // Pagination: Search title with pages.
	public Page<TaskDto> searchPaginatedTasks(String title, Pageable pageable) {
		logger.info("TasksService:searchPaginatedTasks().execution started...");
		Page<Task> tasks = tasksRepository.findByTitleContainingIgnoreCase(title, pageable);
		Page<TaskDto> paginatedTasks = tasks.map(entity -> {
			TaskDto taskDto = toDto(entity);
			return taskDto;
		});

		return paginatedTasks;
	}
   
    // Update single task details
    @CachePut(value = "task", key = "#id")
    public Task updateTaskById(Long id, Task taskDetails) {	
    	logger.info("TasksService:updateTaskById().execution started...id:" + id);
    	
        Optional<Task> existingTask = tasksRepository.findById(id);
        if (existingTask.isPresent()) {
        	Task task = getTaskById(id);
        	task.setTaskTitle(taskDetails.getTaskTitle());
        	task.setTaskDescription(taskDetails.getTaskDescription());
        	task.setTaskStatus(taskDetails.getTaskStatus());
        	logger.info("TasksService:updateTaskById().Update task from database...");
            return tasksRepository.save(task);
        } else {
        	logger.info("TasksService:updateTaskById().execution started...RuntimeException: Task not found!");
            throw new RuntimeException("TasksService:updateTask().Task not found.");
            
        }
    	
        
    }

    // Update bulk task details
    public List<Task> bulkUpdateTasks(List<Task> tasks) {
    	logger.info("TasksService:bulkUpdateTasks().execution started...");
        // Validate and process each item
        for (Task task : tasks) {
            // Example: Check if the item exists before updating
            Optional<Task> existingTask = tasksRepository.findById(task.getTaskId());
            if (existingTask.isPresent()) {
            	Task updatedTask = existingTask.get();
            	updatedTask.setTaskTitle(task.getTaskTitle());
            	updatedTask.setTaskDescription(task.getTaskDescription());
            	updatedTask.setTaskStatus(task.getTaskStatus());
                tasksRepository.save(updatedTask);
            }  else {
                
            	logger.info("TasksService:bulkUpdateTasks().Task not found.");
            	// Proceed to the next task if the current one cannot be found.
                continue;
            }
        }
        return tasksRepository.saveAll(tasks); // Save all updated items
    }
    
    // Single delete task by ID
    @CacheEvict(value = "task", key = "#id")
    public void deleteTask(Long id) {
    	logger.info("TasksService:deleteTask().execution started...");
    	Task task = getTaskById(id);
    	tasksRepository.delete(task);
    }
    
    // Bulk delete tasks by IDs
    public void deleteAllTasks(List<Long> ids) {
    	logger.info("TasksService:deleteAllTasks().execution started...");
    	tasksRepository.deleteAllById(ids);
    }

    // Maps TaskEntity to TaskDto
	@Override
	public TaskDto toDto(Task taskEntity) {
		logger.info("TasksService:toDto().execution started...");
		 return Mappers.getMapper(ISourceTargetMapper.class).toDto(taskEntity);

	}

	// Maps TaskDto to TaskEntity 
	@Override
	public Task toEntity(TaskDto taskDto) {
		logger.info("TasksService:toEntity().execution started...");
		return Mappers.getMapper(ISourceTargetMapper.class).toEntity(taskDto);
	}


	// Example implementation of calling OkHttp HttpClientConnection
	@Override
	public String getHttpClientConnection(String url) {
		logger.info("TasksService:getHttpClientConnection().execution started...");
		String responseBody = null;
		OkHttpClient client = new OkHttpClient();
		// Example: https://api.github.com/users/octocat
		Request request = new Request.Builder().url(url).build();
		// Synchronous call
		try (Response response = client.newCall(request).execute()) {
		    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		    responseBody = response.body().string();
		    System.out.println(responseBody);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("TasksService:getHttpClientConnection().IOException: " + e);
			e.printStackTrace();
		}
		logger.info("TasksService:getHttpClientConnection().responseBody...: " + responseBody);
		return responseBody;
	}
    
}
