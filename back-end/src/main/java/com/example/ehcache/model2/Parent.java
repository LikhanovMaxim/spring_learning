package com.example.ehcache.model2;

import com.example.ehcache.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

@Slf4j
public abstract class Parent {
    @Cacheable
    protected String invoke(String param) {
        Utility.simulateSlowService(2000);
        return param + "parent" + getChild();
    }

    protected abstract String getChild();
}
