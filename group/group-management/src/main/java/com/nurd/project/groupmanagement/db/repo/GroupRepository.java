package com.nurd.project.groupmanagement.db.repo;

import com.nurd.project.groupmanagement.db.entity.GroupEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GroupRepository extends MongoRepository<GroupEntity, String> {
    List<GroupEntity> findByOwnerId(String ownerId);
}
