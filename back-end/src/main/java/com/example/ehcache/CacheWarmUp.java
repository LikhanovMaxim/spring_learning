package com.example.ehcache;

import com.example.ehcache.model1.NumberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
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
    }
}