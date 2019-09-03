package com.example.ehcache.examples.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Book implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    String id;
    String name;
}
