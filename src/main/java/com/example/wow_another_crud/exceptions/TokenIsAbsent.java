package com.example.wow_another_crud.exceptions;

public class TokenIsAbsent extends RuntimeException {
    public TokenIsAbsent() {
        super("Token is not present");
    }
}
