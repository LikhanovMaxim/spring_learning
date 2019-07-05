package com.example.properties;

import com.example.properties.configs.ConfigProperties;
import com.example.properties.configs.ConfigPropertiesYml;
import com.example.properties.configs.MainConfigYml;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class RestPointProperties {
    @Autowired
    private ConfigProperties configurationProperties;

    @Autowired
    private MainConfigYml mainConfigYml;

    @Autowired
    private ConfigPropertiesYml configPropertiesYml;

    @GetMapping("/get/properties/additional")
    public String print() {
        log.info(".properties config {}", configurationProperties);
        log.info(".getStringList config {}", configurationProperties.getStringList());
        log.info(".getStringList().get(0) config {}", configurationProperties.getStringList().get(0));
        return configurationProperties.toString();
    }

    @GetMapping("/get/properties/yml/main")
    public String print2() {
        log.info(".yml config {}", mainConfigYml);
        return mainConfigYml.toString();
    }

    @GetMapping("/get/properties/additional/yml")
    public String print3() {
        log.info(".yml config {}", configPropertiesYml);
        return configPropertiesYml.toString();
    }
}
