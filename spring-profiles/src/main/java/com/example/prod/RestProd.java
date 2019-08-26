package com.example.prod;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("prod")
public class RestProd {

    @GetMapping(value = "prod")
    public String ods() {
        return "it is only for prod profile";
    }

}
