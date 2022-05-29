package com.nurd.project.gateway.service.impl;

import com.nurd.project.common.dto.ToDoDTO;
import com.nurd.project.gateway.service.GroupService;
import com.nurd.project.gateway.service.ResultMessage;
import com.nurd.project.gateway.service.ToDoService;
import com.nurd.project.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    GroupService groupService;

    @Autowired
    UserService userService;

    @Override
    public ResultMessage canCreate(ToDoDTO dto) {
        // group exists //
        if(groupService.getById(dto.getGroupId()) == null)
            return new ResultMessage(false, "Group id can not be found");

        // user exists //
        if(userService.getById(dto.getAssigneeId()) == null)
            return new ResultMessage(false, "Assignee (user) id can not be found");
        if(userService.getById(dto.getReporterId()) == null)
            return new ResultMessage(false, "Reporter (user) id can not be found");

        return new ResultMessage(true, null);
    }
}
