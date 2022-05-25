package com.nurd.project.todogateway.mapper;

import com.nurd.project.todogateway.dto.ToDoDTO;
import com.nurd.project.todomanagement.db.entity.ToDoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper
public abstract class ToDoMapper {
    @Mapping(target = "dueDate", expression = "java(getDueDate(source))")
    public abstract ToDoEntity mapToToDoEntity(ToDoDTO source);

    public Date getDueDate(ToDoDTO source){
        if(source.getDueDate() == null)
            return new Date();
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(source.getDueDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
