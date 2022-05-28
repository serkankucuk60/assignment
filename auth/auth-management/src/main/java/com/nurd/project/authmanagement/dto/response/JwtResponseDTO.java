package com.nurd.project.authmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponseDTO {
    private String token;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;


}
