package com.nurd.project.todogateway.controller;

import com.nurd.project.todogateway.dto.ToDoDTO;
import com.nurd.project.todogateway.mapper.ToDoMapper;
import com.nurd.project.todomanagement.service.ToDoService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/todo")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    private ToDoMapper mapper = Mappers.getMapper(ToDoMapper.class);

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ToDoDTO requestDTO) {
        toDoService.insert(mapper.mapToToDoEntity(requestDTO));
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody ToDoDTO requestDTO) {
        toDoService.update(mapper.mapToToDoEntity(requestDTO));
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody String id) {
        toDoService.delete(id);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @GetMapping("/opens")
    public ResponseEntity<List<ToDoDTO>> getOpenToDos() {
        var list = mapper.mapToToDoEntiyList(toDoService.getOpenToDos());
        return new ResponseEntity<List<ToDoDTO>>(list, HttpStatus.ACCEPTED);
    }

    @PostMapping("/mark-done")
    public ResponseEntity<String> markAsDone(String id) {
        toDoService.markAsDone(id);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @GetMapping("/find-group")
    public ResponseEntity<List<ToDoDTO>> findByGroup(Long groupId) {
        var list = mapper.mapToToDoEntiyList(toDoService.findToDoByGroup(groupId));
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/find-priority")
    public ResponseEntity<List<ToDoDTO>> findByPriority(int priority) {
        var list = mapper.mapToToDoEntiyList(toDoService.findToDoByPriority(priority));
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/find-duedate")
    public ResponseEntity<List<ToDoDTO>> findByDueDate(String dueDate) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dueDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        var list = mapper.mapToToDoEntiyList(toDoService.findToDoByDueDate(date));
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }


}
