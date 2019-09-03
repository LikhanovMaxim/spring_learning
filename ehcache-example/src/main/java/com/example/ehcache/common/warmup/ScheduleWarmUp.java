package com.example.ehcache.common.warmup;

import com.example.ehcache.examples.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScheduleWarmUp {
    private static final int MILLI_SECONDS = 1000;
    private static final int SECONDS = 60;
    //2 minutes:
    private static final int INTERVAL_BETWEEN_TASKS = 2 * SECONDS * MILLI_SECONDS;
    //2 minutes:
    private static final int INITIAL_DELAY = SECONDS * MILLI_SECONDS;

    @Autowired
    private NumberService numberService;

    /**
     * fixedDelay - interval between tasks
     * if 2 min:
     *  - first start: 2019-09-03 14:12:04.950 Start to warm up caches by schedule
     *  - finish task: 2019-09-03 14:12:06.984 end warm up
     *  - second start:2019-09-03 14:14:06.997 Start to warm up caches by schedule
     *
     *  initialDelay
     *  if it is 1 mim:
     *  - app start  : 2019-09-03 14:17:25.020  Started Application in 2.541 seconds (JVM running for 2.852)
     *  - first start: 2019-09-03 14:18:25.001  Start to warm up caches by schedule
     */
    @Scheduled(fixedDelay = INTERVAL_BETWEEN_TASKS, initialDelay = INITIAL_DELAY)
    public void warmUpBySchedule() {
        log.info("Start to warm up caches by schedule");
        numberService.square(30L);
        log.info("end warm up");
    }
}
