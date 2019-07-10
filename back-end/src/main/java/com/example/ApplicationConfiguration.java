package com.example;

import com.example.configuration.ChildFirst;
import com.example.configuration.ChildSecond;
import com.example.configuration.ServiceFirst;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ChildFirst childFirst() {
        ChildFirst childFirst = new ChildFirst();
        childFirst.setMessage("first");
        return childFirst;
    }

    @Bean
    public ChildSecond childSecond() {
        ChildSecond childSecond = new ChildSecond();
        childSecond.setMessage("second");
        return childSecond;
    }

//    @Bean
//    public ServiceFirst serviceFirst(){
//        ServiceFirst serviceFirst = new ServiceFirst();
//
//    }

}
