package com.proper.phip.security.base.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.proper.phip.security.base.service.JwtUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理登录成功的策略
 */
public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler {

    private JwtUserService jwtUserService;

    public JsonLoginSuccessHandler(JwtUserService jwtUserService) {
        this.jwtUserService = jwtUserService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        UserDetails userInfo = (UserDetails) authentication.getPrincipal();
        // 生成token，并返回给前台
        String token = jwtUserService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
        response.setHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> retMap = new HashMap<>();
        List<String> roleIdList = new ArrayList<>();
        for (GrantedAuthority tmp : userInfo.getAuthorities()) {
            roleIdList.add(tmp.getAuthority());
        }
        retMap.put("status", "ok");
        retMap.put("currentAuthority", roleIdList);
        JSONObject responseJson = JSONObject.parseObject(JSON.toJSONString(retMap));
        out.print(responseJson.toString());
        out.flush();
        out.close();
    }
}
