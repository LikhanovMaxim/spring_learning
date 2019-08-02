package com.example.configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChildSecond extends Parent {

    @Override
    protected void invokeChild() {
        log.info("invoke from second child");
    }


}
