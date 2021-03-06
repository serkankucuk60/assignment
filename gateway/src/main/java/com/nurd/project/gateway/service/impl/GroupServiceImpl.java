package com.nurd.project.gateway.service.impl;

import com.nurd.project.common.dto.GroupDTO;
import com.nurd.project.gateway.client.GroupClient;
import com.nurd.project.gateway.service.GroupService;
import com.nurd.project.gateway.service.ResultMessage;
import com.nurd.project.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupClient groupClient;

    @Autowired
    UserService userService;

    @Override
    public GroupDTO getById(String id) {
        try {
            return groupClient.getById(id).getBody();
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultMessage canCreate(GroupDTO dto) {
        // user exists //
        if(userService.getById(dto.getOwnerId()) == null)
            return new ResultMessage(false, "Owner (user) id can not be found");

        return new ResultMessage(true, null);
    }
}
