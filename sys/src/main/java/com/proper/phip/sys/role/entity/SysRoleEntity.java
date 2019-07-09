package com.proper.phip.sys.role.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 权限表
 */
@Table(name = "PHIP_SYS_ROLE")
public class SysRoleEntity extends BaseEntity {

    // 权限编码
    private String roleCode;

    // 权限名称
    private String roleName;

    // 权限名称拼音
    private String roleNamePy;

    // 默认权限  1:是;0:否
    private String isDefault;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleNamePy() {
        return roleNamePy;
    }

    public void setRoleNamePy(String roleNamePy) {
        this.roleNamePy = roleNamePy;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}
