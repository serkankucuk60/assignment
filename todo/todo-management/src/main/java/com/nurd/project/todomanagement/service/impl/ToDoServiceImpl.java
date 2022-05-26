package com.nurd.project.todomanagement.service.impl;

import com.nurd.project.todomanagement.db.entity.ToDoEntity;
import com.nurd.project.todomanagement.service.ToDoService;
import com.nurd.project.todomanagement.utils.ToDoStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Component
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    private com.nurd.project.todomanagement.db.repo.ToDoRepository repository;

    @Override
    public void insert(ToDoEntity entity) {
        repository.insert(entity);
    }

    @Override
    public void update(ToDoEntity entity) {
        repository.save(entity);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<ToDoEntity> getOpenToDos() {
        return repository.findByStatus(ToDoStatusEnum.OPEN.toString());
    }

    @Override
    public void markAsDone(String id) {
        var entity = repository.findById(id).get();
        if(entity != null){
            entity.setStatus(ToDoStatusEnum.DONE.toString());
            repository.save(entity);
        }
    }
    @Override
    public void markAsOpen(String id) {
        var entity = repository.findById(id).get();
        if(entity != null){
            entity.setStatus(ToDoStatusEnum.OPEN.toString());
            repository.save(entity);
        }
    }

    @Override
    public List<ToDoEntity> findToDoByGroup(Long groupId) {
        return repository.findByGroupId(groupId);
    }

    @Override
    public List<ToDoEntity> findToDoByPriority(int priority) {
        return repository.findByPriority(priority);
    }

    @Override
    public List<ToDoEntity> findToDoByDueDate(Date dueDate) {
        return repository.findByDueDate(dueDate);
    }
}
