package com.nurd.project.groupgateway.controller;

import com.nurd.project.groupgateway.dto.GroupDTO;
import com.nurd.project.groupgateway.mapper.GroupMapper;
import com.nurd.project.groupmanagement.service.GroupService;
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
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    private GroupService toDoService;

    private GroupMapper mapper = Mappers.getMapper(GroupMapper.class);

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GroupDTO requestDTO) {
        toDoService.insert(mapper.mapToGroupEntity(requestDTO));
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody GroupDTO requestDTO) {
        toDoService.update(mapper.mapToGroupEntity(requestDTO));
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody String id) {
        toDoService.delete(id);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @GetMapping("/find-owner")
    public ResponseEntity<List<GroupDTO>> findByGroup(Long ownerId) {
        var list = mapper.mapToGroupEntiyList(toDoService.findGroupByOwner(ownerId));
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

}
