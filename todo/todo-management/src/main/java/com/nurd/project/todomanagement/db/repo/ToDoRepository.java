package com.nurd.project.todomanagement.db.repo;

import com.nurd.project.todomanagement.db.entity.ToDoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ToDoRepository extends MongoRepository<ToDoEntity, String> {
    List<ToDoEntity> findByGroupId(String groupId);

    List<ToDoEntity> findByPriority(int priority);

    List<ToDoEntity> findByDueDate(Date dueDate);

    List<ToDoEntity> findByStatus(String status);

}
