package com.nurd.project.gateway.controller;

import com.nurd.project.common.dto.ToDoDTO;
import com.nurd.project.gateway.client.ToDoClient;
import com.nurd.project.gateway.interceptor.ClientHandlerInterceptor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.MappedInterceptor;

import java.util.List;

@RestController
@RequestMapping(value="/todo")
public class ToDoController {
    @Autowired
    ToDoClient client;

    @Bean
    @Autowired
    public HandlerInterceptor getMappedInterceptor(ClientHandlerInterceptor interceptor){
        return new MappedInterceptor(new String[] { "/" }, interceptor);
    }

    @GetMapping("/opens")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<List<ToDoDTO>> getOpenToDos(){
        return client.getOpenToDos();
    }

}
