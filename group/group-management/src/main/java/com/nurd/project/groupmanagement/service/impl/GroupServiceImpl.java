package com.nurd.project.groupmanagement.service.impl;

import com.nurd.project.groupmanagement.db.entity.GroupEntity;
import com.nurd.project.groupmanagement.db.repo.GroupRepository;
import com.nurd.project.groupmanagement.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository repository;

    @Override
    public void insert(GroupEntity entity) {
        repository.insert(entity);
    }

    @Override
    public void update(GroupEntity entity) {
        repository.save(entity);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<GroupEntity> findGroupByOwner(Long ownerId) {
        return repository.findByOwnerId(ownerId);
    }

}
