package com.example.cache.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Service {

    private String message = "default";

    public Service(){
    }

    String concatenation(String additionalMessage) {
        return message + " is a current profile. Your message: " + additionalMessage;
    }
}
