package com.example.ehcache.storage;

import com.example.ehcache.Utility;
import com.example.ehcache.storage.model.Book;
import com.example.ehcache.storage.model.BookStorage;
import com.example.ehcache.storage.model.DataSmth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CacheService {

    List<DataSmth> list;

    @CachePut(value = "retrieveAreas")
    public long addArea(DataSmth dataSmth) {
        // some codes here
        return Long.parseLong(null);
    }

    @CachePut(value = "storage", key = "#id")
    public Book updateBook(int id, String bookName) {
        System.out.println("Executing updateBook method...");
        Book book = new Book();
        book.setId(id);
        book.setName(bookName);
        return book;
    }

    @Autowired
    Utility utility;

    @Cacheable(value = "storage", key = "#id")
    public Book getBook(int id) {
        System.out.println("Executing getBook method...");
        Book book = new Book();
        book.setId(id);
        book.setName("Mahabharat");

        return book;
    }


//    @CachePut(value = "storage2", key = "#id")
//    public BookStorage updateBook2(int id, Book book) {
//        System.out.println("Executing updateBook method...");
//        BookStorage bookStorage = new BookStorage();
//        bookStorage.setId(id);
//        bookStorage.setBook(Collections.singletonList(book));
//        return bookStorage;
//    }
//
//    @Cacheable(value = "storage2", key = "#id")
//    public BookStorage getBook2(int id) {
//        System.out.println("Executing getBook method...");
//        BookStorage bookStorage = new BookStorage();
////        bookStorage.setId(id);
////        bookStorage.setBook();
//
//        return bookStorage;
//    }
}
