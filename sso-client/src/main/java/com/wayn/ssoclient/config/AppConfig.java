package com.wayn.ssoclient.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "sso")
@ComponentScan("com.wayn")
public class AppConfig {
    private String server;

    private int tokenRefreshTime;
}
