package com.example.ehcache.model;


import com.example.ehcache.Utility;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class BookRepositoryImpl implements BookRepository {
    private static final int HOW_MUCH_WAIT = 2000;

    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        Utility.simulateSlowService(HOW_MUCH_WAIT);
        return new Book(isbn, "Some book");
    }

}
