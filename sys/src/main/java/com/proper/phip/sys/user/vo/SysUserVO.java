package com.proper.phip.sys.user.vo;

import com.proper.phip.sys.user.entity.SysUserEntity;

import java.util.List;

public class SysUserVO extends SysUserEntity {

    // 1:只更新用户基本信息
    private String editUserBasic;

    // 权限ID集合 逗号分隔
    private String roleIds;

    // 科室集合
    private List<SysUserDeptVO> depts;

    public List<SysUserDeptVO> getDepts() {
        return depts;
    }

    public void setDepts(List<SysUserDeptVO> depts) {
        this.depts = depts;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getEditUserBasic() {
        return editUserBasic;
    }

    public void setEditUserBasic(String editUserBasic) {
        this.editUserBasic = editUserBasic;
    }
}
