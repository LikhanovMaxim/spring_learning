package com.example.ehcache;

import com.example.ehcache.model.BookRepository;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEnCache {
    private static final Logger logger = LoggerFactory.getLogger(RestEnCache.class);

    @Autowired
    private NumberService numberService;

    @GetMapping("/ehcache/{number}")
    public String getSquare(@PathVariable Long number) {
        logger.info("call numberService to square {}", number);
        return String.valueOf(numberService.square(number));
    }

    @GetMapping("/ehcache/multiply/{number}")
    public String getMultiply(@PathVariable Long number) {
        logger.info("call numberService to getMultiply {}", number);
        return String.valueOf(numberService.multiply(number, 2));
    }

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/ehcache/object/{number}")
    public String getBook(@PathVariable Long number) {
        logger.info("call numberService to getBook {}", number);
        val book = bookRepository.getByIsbn(String.valueOf(number));
        return book.getId();
    }

}
