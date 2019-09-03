package ru.mvideo.ehcache.monitor.component;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.terracotta.statistics.archive.Timestamped;
import ru.mvideo.ehcache.monitor.utils.DateTimeUtils;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CacheHistoryStore {
    private static final DateTimeFormatter FORMATTER_FOR_GROUPING = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final Map<String, Map<String, Timestamped<Long>>> storage;
    private final CacheManager cacheManager;

    public CacheHistoryStore(org.springframework.cache.CacheManager cacheManager) {
        this.storage = new HashMap<>();
        this.cacheManager = ((EhCacheCacheManager) cacheManager).getCacheManager();
    }

    @Scheduled(cron = "15,45 * * * * *")
    public void fetch() {
        Arrays.stream(this.cacheManager.getCacheNames())
                .map(this.cacheManager::getCache)
                .forEach(this::saveCache);
    }

    private void saveCache(Cache cache) {
        saveLocalCache(cache);
    }

    private void saveLocalCache(Cache cache) {
        cache.getStatistics().cacheHitOperation().count().history()
                .stream()
                .collect(Collectors.groupingBy(
                        o -> DateTimeUtils.ofPattern(o.getTimestamp(), FORMATTER_FOR_GROUPING)
                ))
                .values()
                .stream()
                .map(list -> list.stream().max(Comparator.comparingLong(Timestamped::getTimestamp)).orElse(null))
                .filter(Objects::nonNull)
                .forEach(cacheHistory -> {
                    Map<String, Timestamped<Long>> map = this.storage.getOrDefault(cache.getName(), new HashMap<>());
                    map.put(DateTimeUtils.ofPattern(cacheHistory.getTimestamp(), FORMATTER_FOR_GROUPING), cacheHistory);
                    if (map.size() > 3) {
                        String removeKey = map.entrySet().stream().min(Comparator.comparingLong(o -> o.getValue().getTimestamp())).map(Map.Entry::getKey).orElse("");
                        map.remove(removeKey);
                    }
                    this.storage.put(cache.getName(), map);
                });
    }

    public List<Timestamped<Long>> get(String name) {
        return this.storage.getOrDefault(name, Collections.emptyMap())
                .values().stream()
                .sorted(Comparator.comparingLong(Timestamped::getTimestamp))
                .collect(Collectors.toList());
    }
}
