package com.wayn.ssoserver.config;

import com.wayn.ssocore.filter.JwtFilter;
import com.wayn.ssocore.service.AuthcationRpcService;
import com.wayn.ssocore.service.UserService;
import com.wayn.ssoserver.interceptor.AuthcationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AuthcationRpcService authcationRpcService;
    @Autowired
    private UserService userService;

    /*@Bean
    public FilterRegistrationBean filterRegistrationBean() {
        SsoFilter ssoFilter = new SsoFilter();
        ssoFilter.setSsoServer(true);
        ssoFilter.setAuthcationRpcService(authcationRpcService);
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(ssoFilter);
        registrationBean.setName("ssoFilter");
        registrationBean.setUrlPatterns(Arrays.asList("/admin/*"));
        return registrationBean;
    }*/

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        JwtFilter jwtFilter = new JwtFilter();
        jwtFilter.setUserService(userService);
        jwtFilter.setSsoServer(true);
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(jwtFilter);
        registrationBean.setName("jwtFilter");
        registrationBean.setUrlPatterns(Arrays.asList("/admin/*"));
        return registrationBean;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/admin");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authcationInterceptor())
//                .addPathPatterns("/**");
    }

    @Bean
    public AuthcationInterceptor authcationInterceptor() {
        return new AuthcationInterceptor();
    }

}
