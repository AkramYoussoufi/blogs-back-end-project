package com.project.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleStudentNotFoundException() {
        Map<String,String> message = new HashMap<>();
        message.put("message","something went wrong dealing with your request");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(message);
    }
}
