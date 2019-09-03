package com.example.ehcache.examples.model2;

import org.springframework.stereotype.Component;

@Component
public class Child2 extends Parent {
    @Override
    protected String getChild() {
        return " second";
    }
}
