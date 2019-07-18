package com.example.ehcache.storage;

import com.example.ehcache.storage.model.Book;
import com.example.ehcache.storage.model.BookStorage;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CacheServiceStorage {


    @CachePut(value = "storage2", key = "#id")
    public BookStorage updateBook2(int id, Book book) {
        System.out.println("Executing updateBook method...");
        BookStorage bookStorage = new BookStorage();
        bookStorage.setId(id);
        bookStorage.setBook(Collections.singletonList(book));
        return bookStorage;
    }

    @Cacheable(value = "storage2", key = "#id")
    public BookStorage getBook2(int id) {
        System.out.println("Executing getBook method...");
        BookStorage bookStorage = new BookStorage();
//        bookStorage.setId(id);
//        bookStorage.setBook();

        return bookStorage;
    }
}
