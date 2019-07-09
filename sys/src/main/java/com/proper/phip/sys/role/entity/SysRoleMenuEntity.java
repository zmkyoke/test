package com.proper.phip.sys.role.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 权限菜单表
 */
@Table(name = "PHIP_SYS_ROLE_MENU")
public class SysRoleMenuEntity extends BaseEntity {

    // 权限id  表：PHIP_SYS_ROLE
    private String roleId;

    // 菜单id  表：PHIP_SYS_MENU
    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
