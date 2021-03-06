package com.example.ehcache.examples.example2_valueIsObject.model;


import com.example.ehcache.common.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookRepositoryImpl implements BookRepository {
    private static final int HOW_MUCH_WAIT = 2000;

    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        Utility.simulateSlowService(HOW_MUCH_WAIT);
        return new Book(isbn, "Some book");
    }

}
