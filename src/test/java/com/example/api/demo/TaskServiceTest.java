/**
 * 
 */
package com.example.api.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.api.demo.entity.Task;
import com.example.api.demo.repository.TasksRepository;
import com.example.api.demo.service.impl.TasksServiceImpl;

/**
 * 
 */
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
	
	private Logger logger = LogManager.getLogger(TaskServiceTest.class);
	
	@Mock
	private TasksRepository tasksRepository; // Mock the dependency

    @InjectMocks
    private TasksServiceImpl tasksService; // Inject mocks into this

    @Test
    void shouldReturnTaskWhenFound() {
    	
    	logger.info("TasksService:shouldReturnTaskWhenFound().execution started...");
    	tasksService.getHttpClientConnection("https://api.github.com/users/octocat");
    	
        // 1. Arrange: Define mock behavior
        Task expectedTask = new Task(1L, "Demo-Task-Title-2", "Demo-Task description-2-Description should have at least 50 characters", "Pending");
        when(tasksRepository.findById(1L)).thenReturn(Optional.of(expectedTask));

        // 2. Act: Call the method under test
        Task result = tasksService.getTaskById(1L);

        // 3. Assert: Check the result
        assertEquals("Demo-Task-Title-2", result.getTaskTitle());
        verify(tasksRepository).findById(1L); // Verify interaction
    }

}
