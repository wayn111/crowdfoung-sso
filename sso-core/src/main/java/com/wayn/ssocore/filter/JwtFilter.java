package com.wayn.ssocore.filter;

import com.caucho.hessian.client.HessianProxyFactory;
import com.wayn.ssocore.entity.User;
import com.wayn.ssocore.service.UserService;
import com.wayn.ssocore.util.JwtUtil;
import com.wayn.ssocore.util.UrlUtil;
import lombok.Data;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Objects;

@Data
public class JwtFilter implements Filter {

    private String ssoServerUrl;

    private UserService userService;

    private int tokenRefreshTime;

    private boolean isSsoServer = false;

    @Override
    public void init(FilterConfig filterConfig) {
        if (isSsoServer) {
            ssoServerUrl = filterConfig.getServletContext().getContextPath();
        }
        if (StringUtils.isEmpty(ssoServerUrl)) {
            throw new RuntimeException("客户端ssoServerUrl参数不能为空！");
        }
        if (Objects.isNull(userService)) {
            try {
                userService = (UserService) new HessianProxyFactory().create(UserService.class,
                        ssoServerUrl + "/hessian/userRpcService");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("userRpcService初始化失败！");
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Cookie cookie = WebUtils.getCookie(httpServletRequest, "jwtToken");
        String jwtToken = Objects.nonNull(cookie) ? cookie.getValue() : null;
        if (StringUtils.isEmpty(jwtToken)) {
            httpServletResponse.sendRedirect(ssoServerUrl + "/jwtLogin?" + "backUrl=" + URLEncoder.encode(UrlUtil.getBackUrl(httpServletRequest), "UTF-8"));
            return;
        }
        String username = JwtUtil.getUsername(jwtToken);
        Date issuedAt = JwtUtil.getIssuedAt(jwtToken);
        User user = userService.getUserByUserName(username);
        if (!JwtUtil.verify(jwtToken, user.getUserName(), user.getPassword())) {
            httpServletResponse.sendRedirect(ssoServerUrl + "/jwtLogin?" + "backUrl=" + URLEncoder.encode(UrlUtil.getBackUrl(httpServletRequest), "UTF-8"));
            return;
        }
        /**
         * 刷新快过期的token
         */
        if ((new Date().getTime() - issuedAt.getTime()) > tokenRefreshTime * 60 * 1000) {
            String sign = JwtUtil.sign(user.getUserName(), user.getPassword());
            Cookie newCookie = new Cookie("jwtToken", sign);
            newCookie.setPath("/");
            newCookie.setHttpOnly(true);
            httpServletResponse.addCookie(newCookie);
        }
        request.setAttribute("username", username);
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
