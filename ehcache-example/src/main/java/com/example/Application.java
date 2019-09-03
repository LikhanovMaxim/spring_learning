package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@ConditionalOnClass(name = {
        "net.sf.ehcache.Ehcache",
        "org.springframework.cache.CacheManager",
        "org.springframework.cache.ehcache.EhCacheCacheManager"})
@EnableScheduling
public class Application extends SpringBootServletInitializer {
    //Todo: divide examples
//    TODO add javadoc
//    TODO readme
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
