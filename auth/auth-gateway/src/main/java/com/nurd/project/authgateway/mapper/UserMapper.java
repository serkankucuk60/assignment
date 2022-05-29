package com.nurd.project.authgateway.mapper;

import com.nurd.project.authmanagement.entity.User;
import com.nurd.project.common.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class UserMapper {

    @Mapping(target = "id", expression = "java(source.getId().toString())")
    public abstract UserDTO mapToUserDTO(User source);
}
