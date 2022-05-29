package com.nurd.project.gateway.client;

import com.nurd.project.common.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${client.user-api.name}", url = "${client.user-api.server-url}")
public interface UserClient {
    @GetMapping
    ResponseEntity<UserDTO> getById(@RequestParam Long id);
}
