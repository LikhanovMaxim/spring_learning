package com.example.configuration;

import com.example.configuration.abstraction.ServiceCommon;
import com.example.configuration.abstraction.ServiceFirst;
import com.example.configuration.abstraction.ServiceSecond;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ChekingConfRest {
    @Autowired
    private ServiceCommon serviceCommon;

    @Autowired
    private ServiceCommon serviceCommonSecond;

    @GetMapping("/check/service/common")
    public String checkServiceCommon() {
        log.info("start check ServiceCommon:");
        serviceCommon.getParent().invoke();
        log.info("start check ServiceCommon Second:");
        serviceCommonSecond.getParent().invoke();
        return "look at log";
    }

    @Autowired
    private ServiceFirst serviceFirst;

    @Autowired
    private ServiceSecond serviceSecond;

    @GetMapping("/check")
    public String check() {
        log.info("start check first service:");
        serviceFirst.getParent().invoke();
        log.info("start check second service:");
        serviceSecond.getParent().invoke();
        return "look at log";
    }

}
