package com.nurd.project.gateway.controller;

import com.nurd.project.common.dto.GroupDTO;
import com.nurd.project.gateway.client.GroupClient;
import com.nurd.project.gateway.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    GroupClient client;

    @Autowired
    GroupService groupService;

    @PostMapping("/create")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<String> create(@RequestBody GroupDTO requestDTO) {
        var result = groupService.canCreate(requestDTO);

        if (result.isResult() == false)
            return new ResponseEntity<>(result.getMessage(), HttpStatus.NOT_ACCEPTABLE);

        return client.create(requestDTO);
    }

    @PostMapping("/update")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<String> update(GroupDTO requestDTO) {
        return client.update(requestDTO);
    }

    @PostMapping("/delete")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<String> delete(String id) {
        return client.delete(id);

    }

    @GetMapping("/find-owner")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<List<GroupDTO>> findByGroup(String ownerId) {
        return client.findByGroup(ownerId);
    }

    @GetMapping("/find-all")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<List<GroupDTO>> findAll(int pageNumber, int pageCount, String orderedColumnName) {
        return client.findAll(pageNumber, pageCount, orderedColumnName);
    }

    @GetMapping
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<GroupDTO> getById(@RequestParam(value = "id") String id) {
        return client.getById(id);
    }
}
