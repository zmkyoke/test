package com.proper.phip.security.base.config;

import com.proper.phip.security.base.service.JwtUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token验证成功刷新的策略
 */
public class JwtRefreshSuccessHandler implements AuthenticationSuccessHandler {

    private JwtUserService jwtUserService;

    public JwtRefreshSuccessHandler(JwtUserService jwtUserService) {
        this.jwtUserService = jwtUserService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        // token有效期自动刷新
        jwtUserService.refreshUserLoginInfo((UserDetails)authentication.getPrincipal());
    }
}
