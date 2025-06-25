package com.example.wow_another_crud.utils;

import com.example.wow_another_crud.exceptions.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;

import java.io.IOException;

public class HttpUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void sendHttp(HttpServletRequest request, HttpServletResponse response, int statusCode, String errorMessage) throws IOException {
        var errorResponse = new ErrorResponse(statusCode, errorMessage, request.getRequestURI());
        response.setStatus(statusCode);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}

