package com.example.ehcache.examples.example4.storage.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BookStorage implements Serializable {
    private int id;
    private List<Book> book;
}
