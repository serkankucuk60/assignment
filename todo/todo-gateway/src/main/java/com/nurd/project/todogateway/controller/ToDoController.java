package com.nurd.project.todogateway.controller;

import com.nurd.project.common.dto.ToDoDTO;
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
        var list = mapper.mapToToDoDTOList(toDoService.getOpenToDos());
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<ToDoDTO>>(list, HttpStatus.ACCEPTED);
    }

    @PostMapping("/mark-done")
    public ResponseEntity<String> markAsDone(@RequestParam String id) {
        toDoService.markAsDone(id);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @PostMapping("/mark-open")
    public ResponseEntity<String> markAsOpen(@RequestParam String id) {
        toDoService.markAsOpen(id);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @GetMapping("/find-group")
    public ResponseEntity<List<ToDoDTO>> findByGroup(@RequestParam String groupId) {
        var list = mapper.mapToToDoDTOList(toDoService.findToDoByGroup(groupId));
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/find-priority")
    public ResponseEntity<List<ToDoDTO>> findByPriority(@RequestParam int priority) {
        var list = mapper.mapToToDoDTOList(toDoService.findToDoByPriority(priority));
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/find-duedate")
    public ResponseEntity<List<ToDoDTO>> findByDueDate(@RequestParam String dueDate) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dueDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        var list = mapper.mapToToDoDTOList(toDoService.findToDoByDueDate(date));
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<ToDoDTO>> findAll(@RequestParam int pageNumber, @RequestParam int pageCount, @RequestParam String orderedColumnName) {
        var list = mapper.mapToToDoDTOList(toDoService.findAll(pageNumber, pageCount, orderedColumnName));
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }
}
