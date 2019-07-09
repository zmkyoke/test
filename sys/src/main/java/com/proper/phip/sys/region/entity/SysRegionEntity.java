package com.proper.phip.sys.region.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 分支机构表
 */
@Table(name = "PHIP_SYS_REGION")
public class SysRegionEntity extends BaseEntity {

    // 分支机构编码
    private String regionCode;

    // 分支机构名称
    private String regionName;

    // 分支机构名称拼音
    private String regionNamePy;

    // 所属机构ID  表：PHIP_SYS_COMPANY
    private String companyId;

    // 唯一编码
    private String uniqueCode;

    // 状态  1:正常;0:删除
    private String status;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionNamePy() {
        return regionNamePy;
    }

    public void setRegionNamePy(String regionNamePy) {
        this.regionNamePy = regionNamePy;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
