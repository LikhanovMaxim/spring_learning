package com.example.ehcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventListenerExampleBean {
    private static final Logger logger = LoggerFactory.getLogger(EventListenerExampleBean.class);

    public static int counter;

    @Autowired
    private NumberService numberService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Increment counter");
        numberService.square(20L);
        counter++;
    }
}