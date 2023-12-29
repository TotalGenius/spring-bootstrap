package com.example.bootstrapspring.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @Value("${server.port}")
    private String port;

    @ModelAttribute("serverPort")
    public String getPort() {
        return port;
    }
}
