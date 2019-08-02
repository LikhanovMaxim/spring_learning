package com.example;

import com.example.configuration.ChildFirst;
import com.example.configuration.ChildSecond;
import com.example.configuration.ServiceCommon;
import com.example.configuration.ServiceFirst;
import com.example.configuration.ServiceSecond;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ApplicationConfiguration {

    @Bean
    public ChildFirst childFirst() {
        log.info("creating a bean first child!");
        ChildFirst childFirst = new ChildFirst();
        childFirst.setMessage("first");
        return childFirst;
    }

    @Bean
    public ChildSecond childSecond() {
        log.info("creating a bean second child!");
        ChildSecond childSecond = new ChildSecond();
        childSecond.setMessage("second");
        return childSecond;
    }

    @Bean
    public ServiceCommon serviceCommon() {
        log.info("creating a bean serviceCommon!");
        ServiceCommon serviceCommon = new ServiceCommon();
        serviceCommon.setParent(childFirst());
        return serviceCommon;
    }

    @Bean
    public ServiceCommon serviceCommonSecond() {
        log.info("creating a bean serviceCommon Second!");
        ServiceCommon serviceCommon = new ServiceCommon();
        serviceCommon.setParent(childSecond());
        return serviceCommon;
    }

    //Next example:

    @Bean
    public ServiceFirst serviceFirst() {
        log.info("creating a bean serviceFirst!");
        ServiceFirst serviceFirst = new ServiceFirst();
        serviceFirst.setParent(childFirst());
        return serviceFirst;
    }

    @Bean
    public ServiceSecond serviceSecond() {
        log.info("creating a bean serviceSecond!");
        ServiceSecond serviceSecond = new ServiceSecond();
        serviceSecond.setParent(childSecond());
        return serviceSecond;
    }

    //TODO is it ok to configure beans?
    // Input: abstract class Parent and two his impl ChildFirst, ChildSecond
    // - two Services


}
