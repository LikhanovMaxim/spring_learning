package com.example.ehcache.examples.example1_1problem;

import com.example.ehcache.examples.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RestProblem {
    @Autowired
    private NumberService numberService;

    /**
     * The problem is when we use some service which long response
     * Examples:
     * 1) work with DataBase
     * 2) external service: API.
     * 3) our long calculation
     *
     * @param number what we want to get square
     * @return square of number
     */
    @GetMapping("problem/{number}")
    public String problemSquare(@PathVariable Long number) {
        log.info("call numberService to square {}", number);
        return String.valueOf(numberService.squareWithOutCache(number));
    }

    /**
     * Like problemSquare, but use cache
     */
    @GetMapping("/ehcache/{number}")
    public String getSquare(@PathVariable Long number) {
        log.info("call numberService to square {}", number);
        return String.valueOf(numberService.square(number));
    }

    /**
     * Evict/destroy cache for 'square'
     *
     * @return message
     */
    @GetMapping("/ehcache/evictSquareCache")
    public String evictSquareCache() {
        log.info("evictSquareCache numberService to square ");
        numberService.evictSquareCache();
        return "success";
    }
}
