package com.example.ehcache;

import com.example.ehcache.model.BookRepository;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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

    @GetMapping("/ehcache/concat/{str}")
    public String concat(@PathVariable String str) {
        logger.info("call numberService to concat {}", str);
        List<String> list = Arrays.asList(str, "lol");
        logger.info("lol: {}", Arrays.toString(list.toArray())); //[ler, lol]
        logger.info("second lol: {}", list.toArray()); //[ler, lol]
        return String.valueOf(numberService.concat(list));
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
