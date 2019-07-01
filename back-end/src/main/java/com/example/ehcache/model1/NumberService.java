package com.example.ehcache.model1;

import com.example.ehcache.Utill;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class NumberService {
    private static final Logger logger = LoggerFactory.getLogger(NumberService.class);

    @Getter
    @Setter
    private String field;

    @Cacheable(value = "squareCache", key = "#number", condition = "#number>10")
    public BigDecimal square(Long number) {
        Utill.simulateSlowService(2000);
        BigDecimal square = BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(number));
        logger.info("square of {} is {}", number, square);
        return square;
    }


    @CacheEvict(cacheNames = "squareCache", allEntries = true)
    public void evictSquareCache() {
    }

    @Cacheable(value = "multiply", key = "{#number, #b}")
    public Long multiply(Long number, int b) {
        Utill.simulateSlowService(2000);
        Long res = number * b;
        logger.info("multiply of {} is {}", number, res);
        return res;
    }

    //Arrays.toString(list.toArray()))
//    @Cacheable(value = "concat", key = "java.util.Arrays.toString(#strings.toArray)")
//    @Cacheable(value = "concat", key = "strings")
    //FIXME it is bad solution, what happens if it 3 value or 1?
//    @Cacheable(value = "concat", key = "{#strings.get(0), #strings.get(1)}")
//    @Cacheable(value = "concat", key = "{#strings.toArray()}")
    @Cacheable(value = "concat", key = "{#ids.get(0), ids.get(1)}")
//    public String concat(List<String> strings) {
    public String concat(List<String> ids) {
        Utill.simulateSlowService(2000);
        Arrays.toString(ids.toArray());
        String res = ids.get(0) + ids.get(1);
        logger.info("concat is {}", res);
        return res;
    }

}