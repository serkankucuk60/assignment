package com.nurd.project.todomanagement.service.impl;

import com.nurd.project.todomanagement.db.entity.ToDoEntity;
import com.nurd.project.todomanagement.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    private com.nurd.project.todomanagement.db.repo.ToDoRepository repository;

    @Override
    public void insertToDo(ToDoEntity entity) {
        repository.insert(entity);
    }
}
