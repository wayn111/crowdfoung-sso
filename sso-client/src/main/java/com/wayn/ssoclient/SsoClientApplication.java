package com.wayn.ssoclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SsoClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsoClientApplication.class, args);
    }
}
