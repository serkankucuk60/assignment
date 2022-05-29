package com.nurd.project.gateway.client;

import com.nurd.project.common.dto.GroupDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "${client.group-api.name}", url = "${client.group-api.server-url}")
public interface GroupClient {

    @PostMapping("/create")
    ResponseEntity<String> create(@RequestBody GroupDTO requestDTO);

    @GetMapping
    ResponseEntity<GroupDTO> getById(@RequestParam(value = "id") String id);

    @PostMapping("/update")
    ResponseEntity<String> update(@RequestBody GroupDTO requestDTO);

    @PostMapping("/delete")
    ResponseEntity<String> delete(@RequestParam String id);

    @GetMapping("/find-owner")
    ResponseEntity<List<GroupDTO>> findByGroup(@RequestParam String ownerId);

    @GetMapping("/find-all")
    ResponseEntity<List<GroupDTO>> findAll(@RequestParam int pageNumber, @RequestParam int pageCount, @RequestParam String orderedColumnName);

}
