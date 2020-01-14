package com.wayn.ssoserver.controller;

import com.wayn.ssoserver.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping
    public String admin(HttpServletRequest request) {
        Map<String, Object> users = tokenManager.getAllUser();
        request.setAttribute("users", users);
        return "admin/admin";
    }
}
