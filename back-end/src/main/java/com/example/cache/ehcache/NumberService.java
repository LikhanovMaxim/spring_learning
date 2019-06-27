package com.example.cache.ehcache;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NumberService {
    private static final Logger logger = LoggerFactory.getLogger(NumberService.class);

    @Getter
    @Setter
    private String field;

    @Cacheable(value = "squareCache",
            key = "#number",
            condition = "#number>10")
    public BigDecimal square(Long number) {
        simulateSlowService(2000);
        BigDecimal square = BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(number));
        logger.info("square of {} is {}", number, square);
        return square;
    }

    @Cacheable(value = "multiply",
            key = "{#number, #b}")
    public Long multiply(Long number, int b) {
        simulateSlowService(2000);
        Long res = number * b;
        logger.info("multiply of {} is {}", number, res);
        return res;
    }

    // Don't do this at home
    private void simulateSlowService(long howMuchWait) {
        logger.info("you want to waited for {} ms", howMuchWait);
        try {
            Thread.sleep(howMuchWait);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
