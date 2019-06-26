package com.example;

import com.example.cache.spring.model.BookRepository;
import com.example.cache.spring.model.BookRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    public static final int SECONDS_BETWEEN_CLEAR_CACHES = 10;

    @Bean
    public BookRepository bookRepository() {
        return new BookRepositoryImpl();
    }
}
