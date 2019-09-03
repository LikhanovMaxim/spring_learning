package com.example.ehcache.examples.example2_valueIsObject.model;

public interface BookRepository {
    Book getByIsbn(String isbn);
}
