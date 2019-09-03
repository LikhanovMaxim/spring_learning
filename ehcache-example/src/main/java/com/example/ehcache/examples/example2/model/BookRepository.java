package com.example.ehcache.examples.example2.model;

public interface BookRepository {
    Book getByIsbn(String isbn);
}
