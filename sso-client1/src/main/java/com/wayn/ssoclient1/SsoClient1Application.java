package com.wayn.ssoclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SsoClient1Application {
    public static void main(String[] args) {
        SpringApplication.run(SsoClient1Application.class, args);
    }
}
