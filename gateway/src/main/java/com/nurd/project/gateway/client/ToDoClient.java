package com.nurd.project.gateway.client;

import com.nurd.project.common.dto.ToDoDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "${client.todo-api.name}", url = "${client.todo-api.server-url}")
public interface ToDoClient {

    @GetMapping("/opens")
    ResponseEntity<List<ToDoDTO>> getOpenToDos();
}
