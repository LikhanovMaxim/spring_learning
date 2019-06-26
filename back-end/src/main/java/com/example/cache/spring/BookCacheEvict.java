package com.example.cache.spring;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class BookCacheEvict {

    @CacheEvict(cacheNames = "books", allEntries = true)
    public void evictBooks() {
    }
}
