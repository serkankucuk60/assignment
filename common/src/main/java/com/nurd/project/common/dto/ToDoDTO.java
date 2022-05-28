package com.nurd.project.common.dto;

import lombok.Data;

@Data
public class ToDoDTO {
    private String id;
    private String assigneeId;
    private String reporterId;
    private String groupId;
    private String status;
    private String dueDate;
    private int priority;
}