package com.nurd.project.gateway.service;

import com.nurd.project.common.dto.UserDTO;

public interface UserService {
    UserDTO getById(String id);
}
