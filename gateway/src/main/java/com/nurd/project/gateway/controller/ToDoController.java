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

        if(result.isResult() == false)
            return new ResponseEntity<>(result.getMessage(), HttpStatus.NOT_ACCEPTABLE);

        return toDoClient.create(requestDTO);
    }

}
