package com.example.studentmanager.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model, RedirectAttributes redirectAttributes) {
        String errorMessage = e.getMessage() != null ? e.getMessage() : "Lỗi không xác định";
        String stackTrace = getStackTrace(e);
        
        System.err.println("=== ERROR ===");
        System.err.println("Message: " + errorMessage);
        System.err.println("Stack trace: " + stackTrace);
        System.err.println("=============");
        
        model.addAttribute("message", errorMessage);
        model.addAttribute("error", stackTrace);
        
        return "error";
    }

    private String getStackTrace(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.getClass().getName()).append(": ").append(e.getMessage()).append("\n");
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append("  at ").append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
