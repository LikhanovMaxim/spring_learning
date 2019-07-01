package com.example.ehcache;

import com.example.ehcache.model1.NumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CacheWarmUp {
    private static final Logger logger = LoggerFactory.getLogger(CacheWarmUp.class);

    @Autowired
    private NumberService numberService;

    /**
     * After start application to launch this method (by ApplicationReadyEvent.class)
     */
    @EventListener(ApplicationReadyEvent.class)
    public void warmUpCache() {
        logger.info("Warm up");
        numberService.square(20L);
    }
}