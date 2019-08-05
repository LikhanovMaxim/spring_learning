package com.example.configuration.abstraction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChildFirst extends Parent {

    @Override
    protected void invokeChild() {
        log.info("invoke from first child");
    }
}
