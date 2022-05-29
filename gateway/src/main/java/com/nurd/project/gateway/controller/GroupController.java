package com.nurd.project.gateway.controller;

import com.nurd.project.common.dto.GroupDTO;
import com.nurd.project.gateway.client.GroupClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    GroupClient client;

    @GetMapping
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<GroupDTO> getById(@RequestParam(value = "id") String id){
        return client.getById(id);
    }
}
