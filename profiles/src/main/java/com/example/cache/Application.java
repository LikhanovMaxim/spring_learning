package com.example.cache;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
//@EnableCaching
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        new SpringApplicationBuilder()
                .sources(Application.class)
                .profiles("client")
                .run(args);
    }
}
