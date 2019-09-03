package com.example.ehcache.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Utility {
    private Utility() {
    }

    // Don't do this at home
    public static void simulateSlowService(long howMuchWait) {
        log.info("you want to waited for {} ms", howMuchWait);
        try {
            Thread.sleep(howMuchWait);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
