package com.example.ehcache;

import com.example.ehcache.model2.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Utill {
    private static final Logger logger = LoggerFactory.getLogger(Parent.class);

    // Don't do this at home
    public static void simulateSlowService(long howMuchWait) {
        logger.info("you want to waited for {} ms", howMuchWait);
        try {
            Thread.sleep(howMuchWait);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
