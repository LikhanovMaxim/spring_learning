package com.example.ehcache.examples.example3;

import com.example.ehcache.examples.example3.model.Child1;
import com.example.ehcache.examples.example3.model.Child2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Use Abstract class with cache annotation
 */
@RestController
@Slf4j
public class RestAbstractAndOverride {

    @Autowired
    private Child1 child1;
    @Autowired
    private Child2 child2;

    /**
     * Child use overriding
     *
     * @param str
     * @return
     */
    @GetMapping("/child1/{str}")
    public String child(@PathVariable String str) {
        log.info("child {}", str);
        return child1.invoke(str);
    }

    /**
     * This child use parent cache
     *
     * @param str
     * @return
     */
    @GetMapping("/child2/{str}")
    public String child2(@PathVariable String str) {
        log.info("child {}", str);
        return child2.invoke(str);
    }
}
