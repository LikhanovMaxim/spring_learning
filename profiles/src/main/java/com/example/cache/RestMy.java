package com.example.cache;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestMy {

    @GetMapping(value = "lil")
    public String ods(){
        return "asd";
    }
}
