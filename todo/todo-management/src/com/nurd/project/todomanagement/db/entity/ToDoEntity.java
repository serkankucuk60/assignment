package com.nurd.project.todomanagement.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ToDo")
public class ToDoEntity {
    @Id
    private String id;

    @NotBlank
    private Long assigneeId;

    @NotBlank
    private Long reporterId;

    @NotBlank
    private Long groupId;

    @NotBlank
    private String status;
}
