package com.nurd.project.gateway.client;

import com.nurd.project.common.dto.GroupDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${client.group-api.name}", url = "${client.group-api.server-url}")
public interface GroupClient {
    @GetMapping
    ResponseEntity<GroupDTO> getById(@RequestParam(value = "id") String id);
}
