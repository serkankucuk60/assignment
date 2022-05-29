package com.nurd.project.gateway.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultMessage {
    private boolean result;
    private String message;
}
