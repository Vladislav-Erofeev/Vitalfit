package com.example.sso.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private Long timestamp;

    public ErrorResponse(String message) {
        this.message = message;
        timestamp = System.currentTimeMillis();
    }
}
