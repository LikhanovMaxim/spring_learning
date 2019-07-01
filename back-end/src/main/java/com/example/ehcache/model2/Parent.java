package com.example.ehcache.model2;

import com.example.ehcache.Utill;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;

@Log4j2
public abstract class Parent {
    @Cacheable
    protected String invoke(String param) {
        Utill.simulateSlowService(2000);
        return param + "parent" + getChild();
    }

    protected abstract String getChild();
}
