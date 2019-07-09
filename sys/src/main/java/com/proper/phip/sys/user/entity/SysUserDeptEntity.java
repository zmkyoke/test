package com.proper.phip.sys.user.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 用户科室表
 */
@Table(name = "PHIP_SYS_USER_DEPT")
public class SysUserDeptEntity extends BaseEntity {

    // 用户ID  表:PHIP_SYS_USER
    private String userId;

    // 机构ID  表:PHIP_SYS_COMPANY
    private String companyId;

    // 分支机构ID  表:PHIP_SYS_REGION
    private String regionId;

    // 科室ID  表:PHIP_SYS_DEPT
    private String deptId;

    // 主岗  1:是;0:否
    private String mainFlg;

    // 科室负责人  1:是;0:否
    private String leaderFlg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getMainFlg() {
        return mainFlg;
    }

    public void setMainFlg(String mainFlg) {
        this.mainFlg = mainFlg;
    }

    public String getLeaderFlg() {
        return leaderFlg;
    }

    public void setLeaderFlg(String leaderFlg) {
        this.leaderFlg = leaderFlg;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
}
