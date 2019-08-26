package com.example.cache.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RestCommon {

    @Autowired
    Service service;

    @GetMapping(value = "/common/{message}")
    public String asd(@PathVariable String message ){
        return service.concatenation(message);
    }
}
