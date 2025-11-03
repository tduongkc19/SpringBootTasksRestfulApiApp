/**
 * 
 */
package com.example.api.demo.converter;

import org.mapstruct.Mapper;

import com.example.api.demo.dto.TaskDto;
import com.example.api.demo.entity.Task;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote MapStruct is a Java annotation processor used to 
 * generate type-safe and high-performance mappers 
 * for Java bean classes. It simplifies the process 
 * of mapping between different object models, such 
 * as converting entities to DTOs or vice versa, by 
 * eliminating the need for manual coding.
 */
@Mapper
public interface ISourceTargetMapper {

    // Maps Task entity to TaskDto
    TaskDto toDto(Task sourceTask);
    // Maps TaskDto to Task entity 
    Task toEntity(TaskDto targetTaskDto);

}
