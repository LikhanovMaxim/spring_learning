package com.example.ehcache.examples.example4.storage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Book implements Serializable {
    private int id;
    private String name;

    public Book() {
    }
}
