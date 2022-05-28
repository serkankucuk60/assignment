package com.nurd.project.authgateway.controller;

import com.nurd.project.authmanagement.dto.request.LoginRequestDTO;
import com.nurd.project.authmanagement.dto.request.SignupRequestDTO;
import com.nurd.project.authmanagement.dto.response.JwtResponseDTO;
import com.nurd.project.authmanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO request) {
        JwtResponseDTO jwtResponseDTO = authService.authenticateUser(request);
        return ResponseEntity.ok(jwtResponseDTO);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDTO request) {
        return authService.registerUser(request);
    }
}
