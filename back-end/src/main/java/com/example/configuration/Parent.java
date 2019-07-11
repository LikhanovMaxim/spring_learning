package com.example.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
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
