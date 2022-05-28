package com.nurd.project.authmanagement.service;


import com.nurd.project.authmanagement.dto.request.LoginRequestDTO;
import com.nurd.project.authmanagement.dto.request.SignupRequestDTO;
import com.nurd.project.authmanagement.dto.response.JwtResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    JwtResponseDTO authenticateUser(LoginRequestDTO loginRequestDTO);

    ResponseEntity registerUser(SignupRequestDTO signUpRequestDTO);

}
