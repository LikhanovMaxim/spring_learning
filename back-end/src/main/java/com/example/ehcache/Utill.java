package com.example.ehcache;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class Utill {
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
