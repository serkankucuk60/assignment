package com.nurd.project.todomanagement.service;

import com.nurd.project.todomanagement.db.entity.ToDoEntity;

import java.util.Date;
import java.util.List;

public interface ToDoService {
    void insert(ToDoEntity entity);

    void update(ToDoEntity entity);

    void delete(String id);

    List<ToDoEntity> getOpenToDos();

    void markAsDone(String id);

    void markAsOpen(String id);

    List<ToDoEntity> findToDoByGroup(Long groupId);

    List<ToDoEntity> findToDoByPriority(int priority);

    List<ToDoEntity> findToDoByDueDate(Date dueDate);
}
