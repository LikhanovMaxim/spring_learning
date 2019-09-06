package com.example.ehcache.examples.example3_abstract.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Child2 extends Parent {

    @Override
    public String invoke(String param) {
        log.info("child2");
        return super.invoke(param);
    }

    @Override
    protected String getChild() {
        return " second";
    }
}
