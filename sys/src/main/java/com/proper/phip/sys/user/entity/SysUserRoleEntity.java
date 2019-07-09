package com.proper.phip.sys.user.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 用户权限表
 */
@Table(name = "PHIP_SYS_USER_ROLE")
public class SysUserRoleEntity extends BaseEntity {

    // 用户或科室ID  表:PHIP_SYS_USER或PHIP_SYS_DEPT
    private String userOrDeptId;

    // 权限类别  USER:用户权限;DEPT:科室权限
    private String roleType;

    // 权限ID
    private String roleId;

    public String getUserOrDeptId() {
        return userOrDeptId;
    }

    public void setUserOrDeptId(String userOrDeptId) {
        this.userOrDeptId = userOrDeptId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
