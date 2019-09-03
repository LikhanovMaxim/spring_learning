package com.example.ehcache.storage;

import com.example.ehcache.storage.model.Book;
import com.example.ehcache.storage.model.BookStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheServiceTest {

    @Autowired
    private CacheService cacheService;

//    @Test
//    public void name() {
//        BookStorage bookStorage = cacheService.getBook2(1);
//        assertEquals(new BookStorage(), bookStorage);
//    }
//
//    @Test
//    public void name2() {
//        Book book = new Book(23, "lol");
//        cacheService.updateBook2(1, book);
//        BookStorage bookStorage = cacheService.getBook2(1);
//        assertEquals(book, bookStorage.getBook().get(0));
//    }
}