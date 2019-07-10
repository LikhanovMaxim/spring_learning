package com.example.configuration;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ChildSecond extends Parent {

    @Override
    protected void invokeChild() {
        log.info("invoke from second child");
    }


}
