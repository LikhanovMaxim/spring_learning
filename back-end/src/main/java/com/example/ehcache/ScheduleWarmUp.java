package com.example.ehcache;

import com.example.ehcache.model1.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduleWarmUp {
    private static final int MILLI_SECONDS = 1000;
    private static final int SECONDS = 60;
    private static final int INTERVAL_BETWEEN_TASKS = 60 * SECONDS * MILLI_SECONDS;
    private static final int INITIAL_DELAY = 2 * SECONDS * MILLI_SECONDS;

    @Autowired
    private NumberService numberService;

    @Scheduled(fixedDelay = INTERVAL_BETWEEN_TASKS, initialDelay = INITIAL_DELAY)
    public void warmUpBySchedule() {
        log.info("Start to warm up caches by schedule");
        numberService.square(20L);
    }
}
