package com.nurd.project.authgateway.controller;

import com.nurd.project.authgateway.mapper.UserMapper;
import com.nurd.project.authmanagement.dto.request.LoginRequestDTO;
import com.nurd.project.authmanagement.dto.request.SignupRequestDTO;
import com.nurd.project.authmanagement.dto.response.JwtResponseDTO;
import com.nurd.project.authmanagement.entity.User;
import com.nurd.project.authmanagement.service.AuthService;
import com.nurd.project.common.dto.UserDTO;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO request) {
        JwtResponseDTO jwtResponseDTO = authService.authenticateUser(request);
        return ResponseEntity.ok(jwtResponseDTO);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDTO request) {
        return authService.registerUser(request);
    }

    @GetMapping
    public ResponseEntity<UserDTO> getById(@RequestParam Long id){
        User user = authService.getById(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(mapper.mapToUserDTO(user), HttpStatus.ACCEPTED);
    }
}
