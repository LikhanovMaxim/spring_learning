package com.example.restfull;

import com.example.cache.BookRepository;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
    private static final Logger logger = LoggerFactory.getLogger(HelloRestController.class);

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/")
    String index() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        return "Greetings from Spring Boot! Rest query";
    }

    @GetMapping("/cache")
    String cache(@RequestParam(value = "id", required = false, defaultValue = "1") String id) {
        long start = System.currentTimeMillis();
        logger.info("start to wait for id {}", id);

        val book = bookRepository.getByIsbn(id);

        logger.info("You are waited {} ms", System.currentTimeMillis() - start);
        return book.getId();
    }
}
