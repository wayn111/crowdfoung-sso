package com.wayn.ssoserver.controller;

import com.wayn.ssocore.entity.User;
import com.wayn.ssocore.service.UserService;
import com.wayn.ssoserver.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;


@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;

    @GetMapping("login")
    public String login(@RequestParam() String backUrl, HttpServletRequest request) {
        String token = getTokenByCookie(request);
        if (!StringUtils.isEmpty(token) && tokenManager.validateToken(token)) {
            return "redirect:" + backUrl + "?token=" + token;
        }
        request.setAttribute("backUrl", backUrl);
        return "login";
    }

    @PostMapping("login")
    public String login(
            @RequestParam() String backUrl,
            User loginUser,
            HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {
        if (!StringUtils.pathEquals("admin", loginUser.getUserName()) || !StringUtils.pathEquals("123456", loginUser.getPassword())) {
            request.setAttribute("backUrl", backUrl);
            return "login";
        }
        String token = "";
        User user = userService.getUser(loginUser);
        if (Objects.nonNull(user)) {
            token = tokenManager.generateToken();
            tokenManager.addToken(token, user);
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
        // 跳转到原请求
        backUrl = URLDecoder.decode(backUrl, "utf-8");
        return "redirect:" + backUrl + "?token=" + token;
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        String token = getTokenByCookie(request);
        tokenManager.removeToken(token);
        request.getSession().removeAttribute("sessionUser");
        return "redirect:http://localhost/ssoserver";
    }

    private String getTokenByCookie(HttpServletRequest request) {
        String token = "";
        Cookie[] cookies = request.getCookies();
        if (Objects.nonNull(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }
}
