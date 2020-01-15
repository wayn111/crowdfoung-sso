package com.wayn.ssoserver.interceptor;

import com.wayn.ssocore.entity.User;
import com.wayn.ssocore.service.UserService;
import com.wayn.ssocore.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthcationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwtToken = request.getHeader("jwtToken");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (!StringUtils.isEmpty(jwtToken)) {
            String username = JwtUtil.getUsername(jwtToken);
            User user = userService.getUserByUserName(username);
            if (!JwtUtil.verify(jwtToken, user.getUserName(), user.getPassword())) {
                throw new RuntimeException("非法访问！");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
