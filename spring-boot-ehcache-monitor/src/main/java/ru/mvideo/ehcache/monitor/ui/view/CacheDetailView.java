package ru.mvideo.ehcache.monitor.ui.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import ru.mvideo.ehcache.monitor.ui.view.component.CacheDetailComponent;

@SpringView(name = "detail")
public class CacheDetailView extends CacheDetailComponent {
    public CacheDetailView(CacheManager cacheManager, ObjectMapper objectMapper) {
        super(((EhCacheCacheManager) cacheManager).getCacheManager(), objectMapper);
    }
}
