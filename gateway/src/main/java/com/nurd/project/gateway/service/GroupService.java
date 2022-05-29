package com.nurd.project.gateway.service;

import com.nurd.project.common.dto.GroupDTO;

public interface GroupService {
    GroupDTO getById(String id);

    ResultMessage canCreate(GroupDTO dto);
}
