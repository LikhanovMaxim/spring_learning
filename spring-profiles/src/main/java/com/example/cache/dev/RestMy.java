package com.example.cache.dev;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("dev")
public class RestMy {

    @GetMapping(value = "dev")
    public String ods(){
        return "asd";
    }
}
