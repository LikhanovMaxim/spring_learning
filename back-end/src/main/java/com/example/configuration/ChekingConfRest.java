package com.example.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
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
