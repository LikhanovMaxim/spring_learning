package com.example.cache;

import com.example.cache.common.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @Profile("dev")
    public Service service(){
        return new Service("dev");
    }

    @Profile("prod")
    public Service serviceProd(){
        return new Service("prod");
    }
}
