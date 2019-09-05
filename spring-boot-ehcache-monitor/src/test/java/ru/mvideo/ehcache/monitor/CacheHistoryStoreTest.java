package ru.mvideo.ehcache.monitor;

import lombok.EqualsAndHashCode;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.statistics.StatisticsGateway;
import net.sf.ehcache.statistics.extended.ExtendedStatistics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.terracotta.statistics.archive.Timestamped;
import ru.mvideo.ehcache.monitor.component.CacheHistoryStore;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CacheHistoryStoreTest {

    private static final String CACHE_1 = "Cache1";
    @Mock
    private EhCacheCacheManager springCacheManager;

    @Mock
    private CacheManager cacheManager;

    @Mock
    private StatisticsGateway statisticsGateway;

    @Mock
    private ExtendedStatistics.Result result;
    @Mock
    private ExtendedStatistics.Statistic<Long> statistic;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() {
        when(this.springCacheManager.getCacheManager()).thenReturn(cacheManager);
        when(this.cacheManager.getCacheNames()).thenReturn(new String[]{CACHE_1});
        Cache cache = new MockCache(this.statisticsGateway, CACHE_1, 100, false, false, 0L, 0L);
        when(this.cacheManager.getCache(CACHE_1)).thenReturn(cache);
        when(statisticsGateway.cacheHitOperation()).thenReturn(result);
        when(result.count()).thenReturn(statistic);
        when(statistic.history()).thenReturn(
                Arrays.asList(
                        new MockTimestamped(2L, 10000L),
                        new MockTimestamped(1L, 0L),
                        new MockTimestamped(3L, 50000L),
                        new MockTimestamped(7L, 90000L),
                        new MockTimestamped(5L, 70000L),
                        new MockTimestamped(6L, 80000L),
                        new MockTimestamped(4L, 60000L),
                        new MockTimestamped(8L, 120000L)
                )
        );
        CacheHistoryStore cacheHistoryStore = new CacheHistoryStore(this.springCacheManager);
        cacheHistoryStore.fetch();
        assertEquals(3, cacheHistoryStore.get(CACHE_1).size());
        cacheHistoryStore.get(CACHE_1).forEach(timestamped -> {
            if (timestamped.getTimestamp() < 60000L) {
                assertEquals(3L, (long) timestamped.getSample());
            } else if (timestamped.getTimestamp() < 120000L) {
                assertEquals(7L, (long) timestamped.getSample());
            } else {
                assertEquals(8L, (long) timestamped.getSample());
            }
        });
    }

    @EqualsAndHashCode(callSuper = false)
    private class MockCache extends Cache {

        private final StatisticsGateway statisticsGateway;

        MockCache(StatisticsGateway statisticsGateway, String name, int maxElementsInMemory, boolean overflowToDisk, boolean eternal, long timeToLiveSeconds, long timeToIdleSeconds) {
            super(name, maxElementsInMemory, overflowToDisk, eternal, timeToLiveSeconds, timeToIdleSeconds);
            this.statisticsGateway = statisticsGateway;
        }

        @Override
        public StatisticsGateway getStatistics() {
            return statisticsGateway;
        }
    }

    private class MockTimestamped implements Timestamped<Long> {
        private Long sample;
        private Long timestamp;

        MockTimestamped(Long sample, Long timestamp) {
            this.sample = sample;
            this.timestamp = timestamp;
        }

        @Override
        public Long getSample() {
            return sample;
        }

        @Override
        public long getTimestamp() {
            return timestamp;
        }
    }
}
