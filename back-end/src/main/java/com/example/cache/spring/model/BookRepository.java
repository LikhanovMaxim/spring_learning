package com.example.cache.spring.model;

public interface BookRepository {
    Book getByIsbn(String isbn);
}
