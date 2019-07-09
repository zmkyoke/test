package com.proper.phip.security.base.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理登录失败的策略
 */
public class HttpStatusLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        // 设置返回错误码
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("status", "error");
        retMap.put("currentAuthority", "guest");
        JSONObject responseJson = JSONObject.parseObject(JSON.toJSONString(retMap));
        out.print(responseJson.toString());
        out.flush();
        out.close();
    }
}
