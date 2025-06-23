package com.example.wow_another_crud.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }

    private final String timestamp;
    private final int status;
    private final String error;
    private final String path;

    public ErrorResponse(int status, String error, String path) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.error = error;
        this.path = path;
    }
}