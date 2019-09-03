package com.example.ehcache.warmup;

import com.example.ehcache.examples.model1.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheWarmUp {
    @Autowired
    private NumberService numberService;

    /**
     * After start application to launch this method (by ApplicationReadyEvent.class)
     */
    @EventListener(ApplicationReadyEvent.class)
    public void warmUpCache() {
        log.info("Warm up");
        numberService.square(20L);
        log.info("End warm up");
    }
}