package com.nurd.project.authgateway.mapper;

import com.nurd.project.authmanagement.entity.User;
import com.nurd.project.common.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public abstract class UserMapper {

    @Mapping(target = "id", expression = "java(source.getId().toString())")
    public abstract UserDTO mapToUserDTO(User source);

    public abstract List<UserDTO> mapToUserDTOList(List<User> source);
}
