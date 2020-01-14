package com.wayn.ssoclient1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "sso")
@ComponentScan("com.wayn.ssoclient")
public class AppConfig {
    private String server;
}
