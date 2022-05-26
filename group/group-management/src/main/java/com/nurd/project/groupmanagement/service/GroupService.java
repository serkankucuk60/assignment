package com.nurd.project.groupmanagement.service;

import com.nurd.project.groupmanagement.db.entity.GroupEntity;

import java.util.Date;
import java.util.List;

public interface GroupService {
    void insert(GroupEntity entity);

    void update(GroupEntity entity);

    void delete(String id);

    List<GroupEntity> findGroupByOwner(String ownerId);

    List<GroupEntity> findAll(int pageNumber, int pageSize, String orderedColumnName);
}
