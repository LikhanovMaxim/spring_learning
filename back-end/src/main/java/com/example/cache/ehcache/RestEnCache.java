package com.example.cache.ehcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEnCache {
    private static final Logger logger = LoggerFactory.getLogger(RestEnCache.class);

    @Autowired
    private NumberService numberService;

    @GetMapping("/ehcache/{number}")
    public String getSquare(@PathVariable Long number) {
        logger.info("call numberService to square {}", number);
        return String.valueOf(numberService.square(number));
    }
}
