package com.example.cache.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.example.ApplicationConfiguration.SECONDS_BETWEEN_CLEAR_CACHES;

@Component
public class EvictScheduler {
    private static final Logger logger = LoggerFactory.getLogger(RestExampleCache.class);

    @Autowired
    private BookCacheEvict bookCacheEvict;

    private static final int M_SEC = 1000;

//    @Scheduled(fixedDelay = SECONDS_BETWEEN_CLEAR_CACHES * M_SEC)
//    public void clearCaches() {
//        logger.info("Invalid books caches");
//        bookCacheEvict.evictBooks();
//    }

}
