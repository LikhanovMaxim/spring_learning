package com.example.ehcache.model;


import com.example.restclient.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class BookRepositoryImpl implements BookRepository {
    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);
    private static final int HOW_MUCH_WAIT = 2000;

    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        simulateSlowService(HOW_MUCH_WAIT);
        return new Book(isbn, "Some book");
    }

    // Don't do this at home
    private void simulateSlowService(long howMuchWait) {
        logger.info("you want to waited for {} ms", howMuchWait);
        try {
            Thread.sleep(howMuchWait);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
