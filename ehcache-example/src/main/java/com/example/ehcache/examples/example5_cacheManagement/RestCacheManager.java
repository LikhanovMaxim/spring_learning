package com.example.ehcache.examples.example5_cacheManagement;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.statistics.StatisticsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RestCacheManager {

    @Autowired
    private CacheManager cacheManager;

    /**
     * Example http://localhost:8093/cache/manager/books
     *
     * @param cacheName from ehcache.xml
     * @return message
     */
    @GetMapping("/cache/manager/{cacheName}")
    public String statictis(@PathVariable String cacheName) {
        log.info("Info of {} cache", cacheName);
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            return "wrong name";
        }
        showStatistics(cache);
        return "look at log";
    }

    private void showStatistics(Cache cache) {
        log.info("Size {}", cache.getSize());
        StatisticsGateway statistics = cache.getStatistics();
        long hitCount = statistics.cacheHitCount();
        log.info("Hit. How many times hit to the cache {}", hitCount);
        log.info("Miss: Expire. How many times miss expired cache {}", statistics.cacheMissExpiredCount());
        log.info("Miss: Not Found. How many times not found cache {}", statistics.cacheMissNotFoundCount());
        long cacheMissCount = statistics.cacheMissCount();
        log.info("hit Ratio. How many times miss expired cache or not found cache {}", cacheMissCount);

        String hitRatio = (int) (((double) hitCount) / ((double) (cacheMissCount + hitCount)) * 100) + "%";
        log.info("hit Ratio {}", hitRatio);
    }
}
