package com.nurd.project.gateway.service;

import com.nurd.project.common.dto.ToDoDTO;

public interface ToDoService {
    ResultMessage canCreate(ToDoDTO dto);
}
