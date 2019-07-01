package com.example.ehcache.model2;

import org.springframework.stereotype.Component;

@Component
public class Child2 extends Parent {
    @Override
    protected String getChild() {
        return " second";
    }
}
