package com.wayn.ssoclient.config;

import com.wayn.ssocore.filter.SsoFilter;
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
        SsoFilter ssoFilter = new SsoFilter();
        ssoFilter.setSsoServerUrl(appConfig.getServer());
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(ssoFilter);
        registrationBean.setName("ssoFilter");
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        return registrationBean;
    }
}
