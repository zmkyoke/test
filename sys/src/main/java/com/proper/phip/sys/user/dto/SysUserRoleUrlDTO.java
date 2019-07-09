package com.proper.phip.sys.user.dto;

public class SysUserRoleUrlDTO {

    // 用户名
    private String username;

    // 用户有权限的url
    private String url;

    // 用户有权限的请求类型，为空时表示全部适用
    private String requestMethod;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
