package com.example.ehcache.storage;

import com.example.ehcache.storage.model.Book;
import com.example.ehcache.storage.model.BookStorage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class RestStorage {

    @Autowired
    private CacheService cacheService;

    @GetMapping("storage/put/{id}")
    public String put(@PathVariable Integer id) {
        cacheService.updateBook(id, "lol");
        return "success";
    }

    @GetMapping("storage/get/{id}")
    public Book get(@PathVariable Integer id) {
        log.info("get {}", id);
        Book res = cacheService.getBook(id);
        log.info("book {}", res);
        return res;
    }

    @Autowired
    private CacheServiceStorage cacheServiceStorage;

    @GetMapping("storage/put2/{id}")
    public String put2(@PathVariable Integer id) {
        cacheServiceStorage.updateBook2(id, new Book(23, "lol"));
        return "success";
    }
//
//    @GetMapping("storage/get2/{id}")
//    public String get2(@PathVariable Integer id) {
//        BookStorage bookStorage = cacheServiceStorage.getBook2(id);
//        log.info("book {}", bookStorage);
//        return String.valueOf(bookStorage);
//    }
}

