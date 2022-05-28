package com.nurd.project.authmanagement.dto.response;

import lombok.Data;

@Data
public class MessageResponseDTO {
    private String message;

    public MessageResponseDTO(String message) {
        this.message = message;
    }
}
