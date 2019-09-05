package com.example.ehcache.examples.example3_abstract.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Child1 extends Parent {

    @Cacheable(value = "child1")
    @Override
    public String invoke(String param) {
        log.info("invoke child1");
        return super.invoke(param);
    }

    @Override
    protected String getChild() {
        return " first";
    }
}
