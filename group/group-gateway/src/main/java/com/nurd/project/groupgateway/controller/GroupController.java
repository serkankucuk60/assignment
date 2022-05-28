package com.nurd.project.groupgateway.controller;

import com.nurd.project.common.dto.GroupDTO;
import com.nurd.project.groupgateway.mapper.GroupMapper;
import com.nurd.project.groupmanagement.service.GroupService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    private GroupMapper mapper = Mappers.getMapper(GroupMapper.class);

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GroupDTO requestDTO) {
        groupService.insert(mapper.mapToGroupEntity(requestDTO));
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody GroupDTO requestDTO) {
        groupService.update(mapper.mapToGroupEntity(requestDTO));
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody String id) {
        groupService.delete(id);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @GetMapping("/find-owner")
    public ResponseEntity<List<GroupDTO>> findByGroup(String ownerId) {
        var list = mapper.mapToGroupDTOList(groupService.findGroupByOwner(ownerId));
        if(list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<GroupDTO>> findAll(int pageNumber, int pageCount, String orderedColumnName){
        var list = mapper.mapToGroupDTOList(groupService.findAll(pageNumber, pageCount, orderedColumnName));
        if(list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }
}
