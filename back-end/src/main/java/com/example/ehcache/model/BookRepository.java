package com.example.ehcache.model;

public interface BookRepository {
    Book getByIsbn(String isbn);
}
