package com.nurd.project.todomanagement.db.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ToDo")
@Entity
public class ToDoEntity {
    @Id
    private String id;

    @NotBlank
    private String assigneeId;

    @NotBlank
    private String reporterId;

    @NotBlank
    private String groupId;

    @NotBlank
    private String status;

    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @NotBlank
    private int priority;
}
