package com.example.ehcache.examples.example1_2;

import com.example.ehcache.examples.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RestFewKeys {

    private static final int CONST = 2;
    @Autowired
    private NumberService numberService;

    /**
     * If you want to use few keys
     *
     * @return result of multiply the number and CONST
     */
    @GetMapping("/ehcache/multiply/{number}")
    public String getMultiply(@PathVariable Long number) {
        log.info("call numberService to getMultiply {}", number);
        return String.valueOf(numberService.multiply(number, CONST));
    }
}
