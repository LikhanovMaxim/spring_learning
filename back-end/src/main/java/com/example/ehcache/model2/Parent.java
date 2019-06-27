package com.example.ehcache.model2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

public abstract class Parent {
    private static final Logger logger = LoggerFactory.getLogger(Parent.class);

    @Cacheable
    protected String invoke(String param) {
        simulateSlowService(2000);
        return param; //TODO
    }


    // Don't do this at home
    private void simulateSlowService(long howMuchWait) {
        logger.info("you want to waited for {} ms", howMuchWait);
        try {
            Thread.sleep(howMuchWait);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
