package com.nurd.project.gateway.client;

import com.nurd.project.common.dto.ToDoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "${client.todo-api.name}", url = "${client.todo-api.server-url}")
public interface ToDoClient {

    @GetMapping("/opens")
    ResponseEntity<List<ToDoDTO>> getOpenToDos();

    @PostMapping("/create")
    ResponseEntity<String> create(@RequestBody ToDoDTO requestDTO);

    @PostMapping("/mark-done")
    ResponseEntity<String> markAsDone(@RequestParam String id);

    @PostMapping("/mark-open")
    ResponseEntity<String> markAsOpen(@RequestParam String id);

    @PostMapping("/update")
    ResponseEntity<String> update(@RequestBody ToDoDTO requestDTO);

    @PostMapping("/delete")
    ResponseEntity<String> delete(@RequestBody String id);

    @GetMapping("/find-group")
    ResponseEntity<List<ToDoDTO>> findByGroup(@RequestParam String groupId);

    @GetMapping("/find-priority")
    ResponseEntity<List<ToDoDTO>> findByPriority(@RequestParam int priority);

    @GetMapping("/find-duedate")
    ResponseEntity<List<ToDoDTO>> findByDueDate(@RequestParam String dueDate);

    @GetMapping("/find-all")
    ResponseEntity<List<ToDoDTO>> findAll(@RequestParam int pageNumber, @RequestParam int pageCount, @RequestParam String orderedColumnName);
}
