package com.example.properties.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@Configuration
//@ConfigurationProperties works best with hierarchical properties that all have the same prefix. So we add a prefix of mail.
@ConfigurationProperties(prefix = "config")
//We also use @PropertySource to define the location of our properties file.
// Otherwise, Spring uses the default location (classpath:application.properties)
@PropertySource(ConfigProperties.CLASSPATH_CONFIGPROPS_PROPERTIES)

@Data
public class ConfigProperties {
    public static final String CLASSPATH_CONFIGPROPS_PROPERTIES = "classpath:additional.properties";

    private String hostName;
    private int port;
    private String from;
    private List<String> defaultRecipients;
    private List<String> stringList;
    private Map<String, String> additionalHeaders;
    private Credentials credentials;
}
