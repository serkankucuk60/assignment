package com.nurd.project.gateway.service.impl;

import com.nurd.project.common.dto.UserDTO;
import com.nurd.project.gateway.client.UserClient;
import com.nurd.project.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserClient userClient;

    @Override
    public UserDTO getById(String id) {
        try {
            return userClient.getById(Long.parseLong(id)).getBody();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
