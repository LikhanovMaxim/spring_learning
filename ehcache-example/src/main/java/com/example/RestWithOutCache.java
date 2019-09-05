package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RestWithOutCache {

    @GetMapping("/")
    public String check() {
        log.info("check");
        return "It is example of using ehcache";
    }

    @GetMapping("/{message}")
    public String check2(@PathVariable String message) {
        log.info("check");
        return "It is example of using ehcache. Your URI: " + message;
    }

}
