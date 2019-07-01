package com.example.ehcache.model2;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class Child1 extends Parent {

    @Cacheable(value = "child1")
    public String invoke(String param) {
        return super.invoke(param);
    }

    @Override
    protected String getChild() {
        return " first";
    }
}
