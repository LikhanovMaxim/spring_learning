package com.example;

import com.example.cache.BookRepository;
import com.example.cache.BookRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public BookRepository bookRepository() {
        return new BookRepositoryImpl();
    }
}
