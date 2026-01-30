package org.example.naco.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Basic global error handling.
 * - Shows a user-friendly error page for unexpected exceptions.
 * - Keeps stack traces out of JSP while still preserving the message for debugging.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handle(Exception ex, HttpServletRequest request) {
        request.setAttribute("errorMessage", ex.getMessage());
        return "error/error";
    }
}
