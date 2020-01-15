package com.wayn.ssoclient.config;

import com.wayn.ssocore.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class WebConfig {

    @Autowired
    private AppConfig appConfig;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        JwtFilter jwtFilter = new JwtFilter();
        jwtFilter.setSsoServerUrl(appConfig.getServer());
        jwtFilter.setTokenRefreshTime(appConfig.getTokenRefreshTime());
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(jwtFilter);
        registrationBean.setName("jwtFilter");
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        return registrationBean;
    }
}
