package com.nurd.project.todogateway.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ToDoDTO {
    private String id;
    private Long assigneeId;
    private Long reporterId;
    private Long groupId;
    private String status;
    private String dueDate;
    private int priority;
}
