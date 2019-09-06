package ru.mvideo.ehcache.monitor;

import com.vaadin.spring.boot.VaadinAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.mvideo.ehcache.monitor.component.CacheHistoryStore;

@Configuration
@AutoConfigureBefore(VaadinAutoConfiguration.class)
@ConditionalOnClass(name = {
        "net.sf.ehcache.Ehcache",
        "org.springframework.cache.CacheManager",
        "org.springframework.cache.ehcache.EhCacheCacheManager"})
@Slf4j
public class EhcacheMonitorAutoConfiguration {

    @Configuration
    @EnableScheduling
    static class EnableHistoryConfiguration implements InitializingBean {

        @Bean
        public static CacheHistoryStore cacheHistoryStore(CacheManager cacheManager) {
            return new CacheHistoryStore(cacheManager);
        }

        @Override
        public void afterPropertiesSet() {
            log.debug("{} initialized", getClass().getName());
        }
    }

    @Configuration
    @ComponentScan(basePackages = "ru.mvideo.ehcache.monitor.ui")
    static class MonitorViewConfiguration implements InitializingBean {
        @Override
        public void afterPropertiesSet() {
            log.debug("{} initialized", getClass().getName());
        }
    }
}
