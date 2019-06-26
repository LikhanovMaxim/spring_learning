package com.example.cache.spring;

import com.example.cache.spring.model.BookRepository;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestExampleCache {

    private static final Logger logger = LoggerFactory.getLogger(RestExampleCache.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookCacheEvict bookCacheEvict;

    @GetMapping("/cache")
    String cache(@RequestParam(value = "id", required = false, defaultValue = "1") String id) {
        long start = System.currentTimeMillis();
        logger.info("start to wait for id {}", id);

        val book = bookRepository.getByIsbn(id);

        logger.info("You are waited {} ms", System.currentTimeMillis() - start);
        return book.getId();
    }


    @GetMapping("/cache/evict")
    String evictCache() {
        logger.info("start evict books");
        bookCacheEvict.evictBooks();
        return "success";
    }
}
