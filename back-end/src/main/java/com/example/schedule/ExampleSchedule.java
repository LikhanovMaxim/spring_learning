package com.example.schedule;

import com.example.ehcache.RestEhCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * https://www.callicoder.com/spring-boot-task-scheduling-with-scheduled-annotation/
 */
public class ExampleSchedule {
    private static final Logger logger = LoggerFactory.getLogger(RestEhCache.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final int MILLI_SECONDS = 1000;
    private static final int SECONDS_RATE = 10 * MILLI_SECONDS;
    private static final int SECONDS_DELAY = 20 * MILLI_SECONDS;

    /**
     * fixedRate in milliseconds
     * The fixedRate task is invoked at the specified interval even if the previous invocation of the task
     * is not finished.
     */
    @Scheduled(fixedRate = SECONDS_RATE)
    public void scheduleTaskWithFixedRate() {
        logger.info("Fixed Rate Task :: Execution Time - {}", formatDate());
    }

    private String formatDate() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    /**
     * You can use initialDelay parameter with fixedRate and fixedDelay to delay the
     * first execution of the task with the specified number of milliseconds.
     * <p>
     * In the following example, the first execution of the task will be delayed by 5 seconds
     * and then it will be executed normally at a fixed interval of 2 seconds -
     * # Sample output (Server Started at 10:48:46)
     * Fixed Rate Task with Initial Delay :: Execution Time - 10:48:51
     * Fixed Rate Task with Initial Delay :: Execution Time - 10:48:53
     * Fixed Rate Task with Initial Delay :: Execution Time - 10:48:55
     */
    @Scheduled(fixedRate = SECONDS_RATE, initialDelay = 5000)
    public void scheduleTaskWithInitialDelay() {
        logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", formatDate());
    }

    /**
     * You can execute a task with a fixed delay between the completion of the last invocation
     * and the start of the next, using fixedDelay parameter.
     */
    @Scheduled(fixedDelay = SECONDS_DELAY, initialDelay = 10000)
    public void scheduleTaskWithFixedDelay() {
        logger.info("Fixed Delay Task :: Execution Time - {}", formatDate());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            logger.error("Ran into an error {}", ex);
            throw new IllegalStateException(ex);
        }
    }

    /**
     * If the above simple parameters can not fulfill your needs, then you can use cron expressions
     * to schedule the execution of your tasks.
     * <p>
     * the task to be executed every minute:
     */
    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", formatDate());
    }
}
