package com.proper.phip.sys.user.vo;

import com.proper.phip.sys.user.entity.SysUserDeptEntity;

public class SysUserDeptVO extends SysUserDeptEntity {

    // 机构名称
    private String companyName;

    // 分支机构名称
    private String regionName;

    // 科室名称
    private String deptName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
