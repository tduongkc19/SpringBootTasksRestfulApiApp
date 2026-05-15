package com.example.api.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "roles")
@Data
public class Role {
    @Id
    private String id;
    private ERole role;

}
