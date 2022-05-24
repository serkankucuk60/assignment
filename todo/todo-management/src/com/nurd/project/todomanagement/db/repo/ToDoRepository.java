package com.nurd.project.todomanagement.db.repo;

import com.nurd.project.todomanagement.db.entity.ToDoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDoEntity, String> {

}
