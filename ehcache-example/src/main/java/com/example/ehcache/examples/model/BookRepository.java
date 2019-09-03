package com.example.ehcache.examples.model;

public interface BookRepository {
    Book getByIsbn(String isbn);
}
