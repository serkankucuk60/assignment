package com.nurd.project.gateway.service.impl;

import com.nurd.project.common.dto.GroupDTO;
import com.nurd.project.gateway.client.GroupClient;
import com.nurd.project.gateway.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupClient groupClient;

    @Override
    public GroupDTO getById(String id) {
        try {
            return groupClient.getById(id).getBody();
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
