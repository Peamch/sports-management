package com.sports.sports_management.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
