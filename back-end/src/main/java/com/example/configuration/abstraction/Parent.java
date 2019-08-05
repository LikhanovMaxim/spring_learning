package com.example.configuration.abstraction;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Parent {
    @Setter
    @Getter
    private String message;

    public void invoke() {
        log.info("invoke parent " + message);
        invokeChild();
    }

    protected abstract void invokeChild();


}
