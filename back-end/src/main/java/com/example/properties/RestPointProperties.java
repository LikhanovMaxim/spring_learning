package com.example.properties;

import com.example.properties.configs.ConfigProperties;
import com.example.properties.configs.ConfigPropertiesYml;
import com.example.properties.configs.MainConfigYml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestPointProperties {
    private static final Logger logger = LoggerFactory.getLogger(RestPointProperties.class);

    @Autowired
    private ConfigProperties configurationProperties;

    @Autowired
    private MainConfigYml mainConfigYml;

    @Autowired
    private ConfigPropertiesYml configPropertiesYml;

    @GetMapping("/get/properties/additional")
    public String print() {
        logger.info(".properties config {}", configurationProperties);
        return configurationProperties.toString();
    }

    @GetMapping("/get/properties/yml/main")
    public String print2() {
        logger.info(".yml config {}", mainConfigYml);
        return mainConfigYml.toString();
    }

    @GetMapping("/get/properties/additional/yml")
    public String print3() {
        logger.info(".yml config {}", configPropertiesYml);
        return configPropertiesYml.toString();
    }
}
