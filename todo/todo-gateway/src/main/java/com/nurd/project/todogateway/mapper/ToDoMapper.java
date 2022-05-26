package com.nurd.project.todogateway.mapper;

import com.nurd.project.todogateway.dto.ToDoDTO;
import com.nurd.project.todomanagement.db.entity.ToDoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper
public abstract class ToDoMapper {
    @Mapping(target = "dueDate", expression = "java(getDueDate(source))")
    public abstract ToDoEntity mapToToDoEntity(ToDoDTO source);

    public abstract List<ToDoEntity> mapToToDoEntityList(List<ToDoDTO> source);

    @Mapping(target = "dueDate", expression = "java(getDueDateString(source))")
    public abstract ToDoDTO mapToToDoDTO(ToDoEntity source);

    public abstract List<ToDoDTO> mapToToDoDTOList(List<ToDoEntity> source);

    public Date getDueDate(ToDoDTO source) {
        if (source.getDueDate() == null)
            return new Date();
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(source.getDueDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDueDateString(ToDoEntity source) {
        if (source.getDueDate() == null)
            return "";
        return new SimpleDateFormat("dd/MM/yyyy").format(source.getDueDate());
    }
}
