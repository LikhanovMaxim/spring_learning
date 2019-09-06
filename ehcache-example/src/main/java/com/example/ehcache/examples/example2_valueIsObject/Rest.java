package com.example.ehcache.examples.example2_valueIsObject;

import com.example.ehcache.examples.example2_valueIsObject.model.BookRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Example of https://spring.io/guides/gs/caching/
 * http://localhost:8093/ehcache/object/1
 */
@RestController
@Slf4j
public class Rest {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/ehcache/object/{number}")
    public String getBook(@PathVariable Long number) {
        log.info("call numberService to getBook {}", number);
        val book = bookRepository.getByIsbn(String.valueOf(number));
        return book.getId();
    }

}
