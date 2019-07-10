package com.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFirst {
    @Autowired
    private Parent parent;
}
