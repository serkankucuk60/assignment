package com.nurd.project.gateway.controller;

import com.nurd.project.common.dto.ToDoDTO;
import com.nurd.project.gateway.client.ToDoClient;
import com.nurd.project.gateway.service.ToDoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    ToDoClient toDoClient;

    @Autowired
    ToDoService toDoService;

    @GetMapping("/opens")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<List<ToDoDTO>> getOpenToDos() {
        return toDoClient.getOpenToDos();
    }

    @PostMapping("/create")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<String> create(@RequestBody ToDoDTO requestDTO) {
        var result = toDoService.canCreate(requestDTO);

        if (result.isResult() == false)
            return new ResponseEntity<>(result.getMessage(), HttpStatus.NOT_ACCEPTABLE);

        return toDoClient.create(requestDTO);
    }

    @PostMapping("/mark-done")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<String> markAsDone(String id) {
        return toDoClient.markAsDone(id);
    }

    @PostMapping("/mark-open")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<String> markAsOpen(String id) {
        return toDoClient.markAsOpen(id);
    }

    @PostMapping("/update")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<String> update(@RequestBody ToDoDTO requestDTO) {
        return toDoClient.update(requestDTO);
    }

    @PostMapping("/delete")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<String> delete(@RequestBody String id) {
        return toDoClient.delete(id);
    }

    @GetMapping("/find-group")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<List<ToDoDTO>> findByGroup(@RequestBody String groupId) {
        return toDoClient.findByGroup(groupId);
    }

    @GetMapping("/find-priority")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<List<ToDoDTO>> findByPriority(@RequestBody int priority) {
        return toDoClient.findByPriority(priority);
    }

    @GetMapping("/find-duedate")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<List<ToDoDTO>> findByDueDate(@RequestBody String dueDate) {
        return toDoClient.findByDueDate(dueDate);
    }

    @GetMapping("/find-all")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<List<ToDoDTO>> findAll(@RequestBody int pageNumber, @RequestBody int pageCount, @RequestBody String orderedColumnName) {
        return toDoClient.findAll(pageNumber, pageCount, orderedColumnName);
    }
}
