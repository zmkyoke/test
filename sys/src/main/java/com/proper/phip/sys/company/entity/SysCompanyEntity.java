package com.proper.phip.sys.company.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 机构表
 */
@Table(name = "PHIP_SYS_COMPANY")
public class SysCompanyEntity extends BaseEntity {

    // 机构编码
    private String code;

    // 机构名称
    private String name;

    // 机构名称拼音
    private String namePy;

    // 状态  1:正常;0:删除
    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamePy() {
        return namePy;
    }

    public void setNamePy(String namePy) {
        this.namePy = namePy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
