package com.wayn.ssoserver.config;

import com.wayn.ssocore.filter.SsoFilter;
import com.wayn.ssocore.service.AuthcationRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AuthcationRpcService authcationRpcService;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        SsoFilter ssoFilter = new SsoFilter();
        ssoFilter.setSsoServer(true);
        ssoFilter.setAuthcationRpcService(authcationRpcService);
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(ssoFilter);
        registrationBean.setName("ssoFilter");
        registrationBean.setUrlPatterns(Arrays.asList("/admin/*"));
        return registrationBean;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/admin");
    }
}
