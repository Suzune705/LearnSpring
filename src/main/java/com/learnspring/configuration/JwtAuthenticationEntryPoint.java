package com.learnspring.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnspring.dto.request.ApiResponse;
import com.learnspring.enums.ErrorCodeType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;


public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        ErrorCodeType errorCodeType = ErrorCodeType.UNAUTHENTICATED;
        response.setStatus(errorCodeType.getHttpStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // set content type : JSON
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorCodeType.getCode())
                .message(errorCodeType.getMessage())
                .build();
/*
        Convert to JSON
        Serialize Object to String
        @write : write content to https response's body
*/
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
