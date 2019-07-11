package com.example.configuration;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ChildFirst extends Parent {

    @Override
    protected void invokeChild() {
        log.info("invoke from first child");
    }
}
