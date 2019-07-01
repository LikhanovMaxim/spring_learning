package com.example.properties.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
//@ConfigurationProperties works best with hierarchical properties that all have the same prefix.
// So we add a prefix of mail.
//@ConfigurationProperties(prefix = "smth") Don't add prefix I don't why
@ConfigurationProperties

//We also use @PropertySource to define the location of our properties file.
// Otherwise, Spring uses the default location (classpath:application.properties)
@PropertySource("classpath:configyml.yml")

@Data
public class ConfigPropertiesYml {
    private String hostName;
    private int port;
    private String from;
    private List<String> defaultRecipients;
//    TODO:
//    private Map<String, String> additionalHeaders;
//    private Credentials credentials;
}
