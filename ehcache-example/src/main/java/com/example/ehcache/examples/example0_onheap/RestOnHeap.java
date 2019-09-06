package com.example.ehcache.examples.example0_onheap;

import com.example.ehcache.common.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * http://localhost:8093/problem-onheap/1
 */
@RestController
@Slf4j
public class RestOnHeap {

    private HashMap<Object, Object> cache = new HashMap<>();

    @GetMapping("/problem-onheap/{key}")
    public String problem(@PathVariable Integer key) {
        log.info("key {}, cache {}", key, cache);

        String value = calculate(key);
        return value;
    }

    private String calculate(Integer key) {
        Utility.simulateSlowService(1500);
        return key + " is number what you typed";
    }

    @GetMapping("/onheap/{key}")
    public String withCache(@PathVariable Integer key) {
        log.info("key {}, cache {}", key, cache);

        return calculateWithCache(key);
    }

    private String calculateWithCache(Integer key) {
        Object o = cache.get(key);
        if (o != null) {
            return (String) o;
        }
        String res = calculate(key);
        cache.put(key, res);
        return res;
    }
}
