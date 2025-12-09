package com.learnspring.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;


public class LearnSpringException extends RuntimeException {
    public LearnSpringException(String message) {
        super(message);
    }
}
