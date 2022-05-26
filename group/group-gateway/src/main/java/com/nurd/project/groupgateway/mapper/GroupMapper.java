package com.nurd.project.groupgateway.mapper;

import com.nurd.project.groupgateway.dto.GroupDTO;
import com.nurd.project.groupmanagement.db.entity.GroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper
public abstract class GroupMapper {
    public abstract GroupEntity mapToGroupEntity(GroupDTO source);

    public abstract List<GroupEntity> mapToGroupEntityList(List<GroupDTO> source);

    public abstract GroupDTO mapToGroupDTO(GroupEntity source);

    public abstract List<GroupDTO> mapToGroupEntiyList(List<GroupEntity> source);

}
