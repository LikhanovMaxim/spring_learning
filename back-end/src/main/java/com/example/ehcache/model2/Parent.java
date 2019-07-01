package com.example.ehcache.model2;

import com.example.ehcache.Utill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

public abstract class Parent {
    private static final Logger logger = LoggerFactory.getLogger(Parent.class);

    @Cacheable
    protected String invoke(String param) {
        Utill.simulateSlowService(2000);
        return param + "parent" + getChild();
    }

    protected abstract String getChild();
}
