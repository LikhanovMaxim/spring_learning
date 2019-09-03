package com.example.ehcache.examples.example3.model;

import com.example.ehcache.common.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

@Slf4j
public abstract class Parent {

    @Cacheable("parent")
    public String invoke(String param) {
        Utility.simulateSlowService(2000);
        return param + "parent" + getChild();
    }

    protected abstract String getChild();
}
