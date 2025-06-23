package com.example.wow_another_crud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TokenIsAbsent extends RuntimeException {
    public TokenIsAbsent() {
        super("Authorization token not present.");
    }
}
