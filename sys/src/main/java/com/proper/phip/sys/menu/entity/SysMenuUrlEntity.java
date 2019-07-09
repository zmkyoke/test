package com.proper.phip.sys.menu.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 菜单使用URL表
 */
@Table(name = "PHIP_SYS_MENU_URL")
public class SysMenuUrlEntity extends BaseEntity {

    // 菜单ID  表：PHIP_SYS_MENU
    private String menuId;

    // 页面使用网址
    private String url;

    // 请求类型  为空时表示全部适用
    private String requestMethod;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
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
