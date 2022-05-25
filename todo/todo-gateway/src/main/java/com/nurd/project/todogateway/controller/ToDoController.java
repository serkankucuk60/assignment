package com.nurd.project.todogateway.controller;

import com.nurd.project.todogateway.dto.ToDoDTO;
import com.nurd.project.todogateway.mapper.ToDoMapper;
import com.nurd.project.todomanagement.service.ToDoService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todo")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    private ToDoMapper mapper = Mappers.getMapper(ToDoMapper.class);

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ToDoDTO requestDTO) {
        toDoService.insertToDo(mapper.mapToToDoEntity(requestDTO));
       return new ResponseEntity<String>(HttpStatus.CREATED);
    }
}
