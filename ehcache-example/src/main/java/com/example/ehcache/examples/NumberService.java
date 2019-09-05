package com.example.ehcache.examples;

import com.example.ehcache.common.Utility;
import com.example.ehcache.examples.example1_4.RequestParm;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class NumberService {
    @Getter
    @Setter
    private String field;

    /**
     * It can be any service which work long
     */
    public BigDecimal squareWithOutCache(Long number) {
        Utility.simulateSlowService(2000);
        BigDecimal square = BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(number));
        log.info("square of {} is {}", number, square);
        return square;
    }

    @Cacheable(value = "squareCache",
//            key = "#number",
            condition = "#number>10")
    public BigDecimal square(Long number) {
        return squareWithOutCache(number);
    }


    @CacheEvict(cacheNames = "squareCache", allEntries = true)
    public void evictSquareCache() {
    }


    /**
     * If don't put key:
     * -- Cache put of 'multiply' for item with key 'SimpleKey [10,2]' and value '20'
     * If put key 'key = "{#number, #b}':
     * -- Cache put of 'multiply' for item with key '[10, 2]' and value '20'
     *
     * @param number first
     * @param b      second
     * @return multiply
     */
    @Cacheable(
            value = "multiply"
//            , key = "{#number, #b}"
    )
    public Long multiply(Long number, int b) {
        Utility.simulateSlowService(2000);
        Long res = number * b;
        log.info("multiply of {} is {}", number, res);
        return res;
    }

    /**
     * Example of arrays
     *
     * @param ids
     * @return
     */
    @Cacheable(value = "concat")
    public String concat(List<String> ids) {
        Utility.simulateSlowService(2000);
        Arrays.toString(ids.toArray());
        String res = ids.get(0) + ids.get(1);
        log.info("concat is {}", res);
        return res;
    }

    @Cacheable("requestParam")
    public String ketObject(RequestParm requestParm) {
        Utility.simulateSlowService(2000);
        return "success";
    }
}
